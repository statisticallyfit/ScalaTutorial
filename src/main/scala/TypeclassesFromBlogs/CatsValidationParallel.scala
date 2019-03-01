package TypeclassesFromBlogs

import cats.Semigroup
import cats.SemigroupK
import cats.Applicative
import cats.Apply
import cats.Monad
import cats.data.NonEmptyList
import cats.data.EitherK
import cats.data.Validated
import cats.data.ValidatedNel
import cats.data.Validated.{Invalid, Valid}
import cats.implicits._

/**
  *
  */
//note source: https://www.scala-exercises.org/cats/validated

object CatsValidationParallel {


     case class ConnectionParams(url: String, port: Int)

     case class Address(houseNumber: Int, street: String)
     case class Person(name: String, age: Int, address: Address)


     // note: Problem - yields errors one at a time, we would like to give them
     // simultaneously across independent data.

     /*for {
          url <- config[String]("url")
          port <- config[Int]("port")
     } yield ConnectionParams(url, port)*/


     trait Read[A] {
          def read(s: String): Option[A]
     }
     // providing only String and Int instances for brevity
     object Read {
          def apply[A](implicit readA: Read[A]): Read[A] = readA  //todo what does this do?

          implicit val stringRead: Read[String] = new Read[String]{

               def read(s: String): Option[String] = Some(s)
          }

          implicit val intRead: Read[Int] = new Read[Int] {

               def read(s: String): Option[Int] ={

                    if (s.matches("-?[0-9]+"))
                         Some(s.toInt)
                    else
                         None
               }
          }
     }


     /// ---------------------------------------------------------------------
     // Now enumerate the errors

     // Config value ask - (1) missing field, (2) or not well formed
     sealed abstract class ConfigError
     final case class MissingConfig(field: String) extends ConfigError
     final case class ParseError(field: String) extends ConfigError


     /// ---------------------------------------------------------------------
     // Writing the parser
     case class Config(map: Map[String, String]){

          def parse[A: Read](key: String): Validated[ConfigError, A] ={

               map.get(key) match {
                    case None => Invalid(MissingConfig(key))
                    case Some(value) => Read[A].read(value) match {
                         case None => Invalid(ParseError(key))
                         case Some(a) => Valid(a)
                    }
               }
          }
     }


     // We are enforcing the data pieces are independent by asking for all of it up front
     def parallelValidate[E: Semigroup, A, B, C](v1: Validated[E, A], v2: Validated[E, B])
                                     (f: (A, B) => C): Validated[E, C] ={

          //note: clients want their own way of combining two errors, so we must use
          // a general way to combine - Semigroup!

          (v1, v2) match {
               case (Valid(a), Valid(b)) => Valid(f(a, b))
               case (Valid(_), i @ Invalid(_)) => i
               case (i @ Invalid(_), Valid(_)) => i
               case (Invalid(e1), Invalid(e2)) => Invalid(Semigroup[E].combine(e1, e2))
          }
     }

     object ApplicationInstances {
          // note: using nonempty list to combine config errors, to statically verify that
          // the list is non-empty
          implicit val nelSemigroup: Semigroup[NonEmptyList[ConfigError]] =
          SemigroupK[NonEmptyList].algebra[ConfigError]

          implicit val readString: Read[String] = Read.stringRead
          implicit val readInt: Read[Int] = Read.intRead


          //note: parallelValidate looks a lot like apply map2 function so let's define
          // an applicative instance for validated
          implicit def validatedApplicative[E: Semigroup] = new Applicative[Validated[E, ?]]{

               //this is the parallelvalidated function
               def ap[A, B](f: Validated[E, A => B])(fa: Validated[E, A]): Validated[E, B] ={
                    (fa, f) match {
                         case (Valid(a), Valid(fab)) => Valid(fab(a))
                         case (i @ Invalid(_), Valid(_)) => i
                         case (Valid(_), i @ Invalid(_)) => i
                         case (Invalid(e1), Invalid(e2)) => Invalid(Semigroup[E].combine(e1, e2))
                    }
               }

               def pure[A](x: A): Validated[E, A] = Validated.valid(x)

               override def map[A, B](fa: Validated[E, A])(f: A => B): Validated[E, B] = fa.map(f)

               override def product[A, B](fa: Validated[E, A], fb: Validated[E, B]): Validated[E, (A, B)] =
                    ap(fa.map(a => (b: B) => (a, b)))(fb)
          }

          //validated has no flatmap - need to implement Monad to get it
          implicit def validatedMonad[E]: Monad[Validated[E, ?]] = new Monad[Validated[E, ?]]{

               def flatMap[A, B](fa: Validated[E, A])(f: A => Validated[E, B]): Validated[E, B] ={
                    fa match {
                         case Valid(a) => f(a)
                         case i @ Invalid(_) => i
                    }
               }

               def pure[A](x: A): Validated[E, A] = Valid(x)

               def tailRecM[A, B](a: A)(f: A => Validated[E, Either[A, B]]): Validated[E, B] = ???
          }
     }
     import ApplicationInstances._




     //------------------------------------------------------------------------------

     def main(args: Array[String]) {
          val config = Config(Map(("url", "127.0.0.1"), ("port", "1337")))

          val result1 = parallelValidate(
               config.parse[String]("url").toValidatedNel,
               config.parse[Int]("port").toValidatedNel
          )(ConnectionParams.apply)

          assert(result1.isValid)
          assert(result1.getOrElse(ConnectionParams("", 0)) == ConnectionParams("127.0.0.1", 1337))

          // ------------------------------------
          val badConfig = Config(Map(("endpoint", "127.0.0.1"), ("port", "not a number")))

          val result2 = parallelValidate(
               badConfig.parse[String]("url").toValidatedNel,
               badConfig.parse[Int]("port").toValidatedNel
          )(ConnectionParams.apply)

          println(result2)

          assert(result2.isInvalid)
          assert(result2 == Validated.invalid(NonEmptyList(MissingConfig("url"), List(ParseError("port")))))

          // ------------------------------------

          val personConfig = Config(Map(("name", "cat"), ("age", "not a number"),
               ("houseNumber", "1234"), ("lane", "feline street")))

          val resultPerson: ValidatedNel[ConfigError, Person] =
               Apply[ValidatedNel[ConfigError, ?]].map4(
                    config.parse[String]("name").toValidatedNel,
                    config.parse[Int]("age").toValidatedNel,
                    config.parse[Int]("houseNumber").toValidatedNel,
                    config.parse[String]("street").toValidatedNel
               ) {
                    case (name, age, houseNumber, street) =>
                         Person(name, age, Address(houseNumber, street))
               }


          // -------------------------------------

          val v = validatedMonad.tuple2(Validated.invalidNel[String, Int]("oops"),
               Validated.invalidNel[String, Double]("uh oh"))

          println(v) // note - this one short-circuits


          // ------------------------------------
          // sequential validation (no short-circuiting)
          val houseConfig = Config(Map("house_number" → "-42"))

          val houseNumber = houseConfig.parse[Int]("house_number").andThen { n ⇒
               if (n >= 0) Validated.valid(n)
               else Validated.invalid(ParseError("house_number"))
          }

          println(houseNumber)

          assert(!houseNumber.isValid)
          assert(houseNumber == Validated.invalid(ParseError("house_number")))


          // ------------------------------------
          //note - withEither method allows you to turn a Validated instance into
          // an Either instance (temporarily) and apply it to function so that we
          // can get Either's short-circuiting behavior when using Validated type.

          def positive(field: String, i: Int): Either[ConfigError, Int] ={
               if(i >= 0) Either.right(i)
               else Either.left(ParseError(field))
          }

          val houseConfig2 = Config(Map("house_number" -> "-42"))

          val houseNumber2 = houseConfig2.parse[Int]("house_number").withEither {
               either: Either[ConfigError, Int] =>
                    either.flatMap {
                         i => positive("house_number", i)
                    }
          }

          println(houseNumber2)



     }

}