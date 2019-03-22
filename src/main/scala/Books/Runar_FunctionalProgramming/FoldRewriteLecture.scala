package Books.Runar_FunctionalProgramming

import scala.annotation.tailrec

/**
  *
  */
object FoldRewriteLecture {

	 // Main point: fold left is tail recursive on List[T]
	 @tailrec
	 def foldLeft2[A, B](f: (A, B) => A)(acc: A)(values: List[B]): A = {
		  values match {
			   case Nil => acc
			   case x :: xs => foldLeft2(f)(f(acc, x))(xs)
		  }
	 }

	 def main(args: Array[String]) {
		  foldLeft2[Int, Int]({case (acc, theHead) =>
			   println(s"Called for acc $acc and head $theHead")
			   acc + theHead
		  }) (0) (List(7, 8, 9))

	 }
}
