package Books.ScalaForTheImpatient_CayHorstmann.chapter21_implicits

import scala.language.implicitConversions
import scala.io.StdIn

//source:
// https://github.com/BasileDuPlessis/scala-for-the-impatient/blob/master/src/main/scala/com/basile/scala/ch21/Ex04.scala


object Exercise4_FluentReader {


     object Read {
          def readString(prompt: String):String = StdIn.readLine(prompt)
          def readInt(prompt:String):Int        = StdIn.readLine(prompt).toInt
          def readDouble(prompt:String):Double  = StdIn.readLine(prompt).toDouble
     }

     abstract class TypeAsk
     object aString extends TypeAsk
     object anInt   extends TypeAsk
     object aDouble extends TypeAsk

     type Reader = Read.type

     trait LoggerComponent {
          def log(msg:String)
     }


     class FluentReader(reader:Reader) /*extends  LoggerComponent*/ {
          this: LoggerComponent =>

          abstract class FluentAsk { def askingFor(prompt:String):Reader }

          def and(inType: TypeAsk): FluentAsk = in(inType)

          //help todo how does this return abstract object? Abstract cannot be instantiated....
          def in(inType: TypeAsk): FluentAsk = new FluentAsk {

               override def askingFor(prompt: String): Reader = {
                    val res = inType match {
                         case _:anInt.type   => reader.readInt(prompt)
                         case _:aDouble.type => reader.readDouble(prompt)
                         case _              => reader.readString(prompt)
                    }

                    log(prompt + " -> " + res.toString)
                    reader
               }
          }
     }

     //help todo why do we say "with Logger" and why put log method here? why no override?
     implicit def readerToFluentReader(reader:Reader):FluentReader = new FluentReader(reader) with LoggerComponent {
          def log(msg:String) {println(msg)}
     }


     def main(args: Array[String]) {
          Read in aString askingFor "Your name: " and anInt askingFor "Your age: " and aDouble askingFor "Your weight: "
          Console.println()
          Read.in(aString).askingFor("Name: ").and(anInt).askingFor("Age: ").and(aDouble).askingFor("Weight: ")
     }
}
