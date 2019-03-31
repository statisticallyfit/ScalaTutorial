package Books.Runar_FunctionalProgramming

import scala.annotation.tailrec

/**
  *
  */
object Exercise_3_10_FoldsRewrite {
	// Main point: fold left is tail recursive on List[T]
	@tailrec
	def myFoldLeft[A, B](f: (B, A) => B)(acc: B)(values: List[A]): B = {
		values match {
			case Nil => acc
			case x :: xs => myFoldLeft (f) (f(acc, x)) (xs)
		}
	}

	@tailrec
	def myFoldRight[A, B](f: (A, B) => B)(acc: B)(values: List[A]): B ={
		values match {
			case Nil => acc
			case x :: xs => myFoldRight (f) (f(x, acc)) (xs)
		}
	}

	def main(args: Array[String]) {
		var res = 0
		res = myFoldLeft[Int, Int]({case (acc, x) =>
			println(s"Called for acc $acc and head $x")
			acc + x
		}) (0) (List(1, 2, 3, 4, 5))
		println(res)

		res = myFoldRight[Int, Int]({case (x, acc) =>
			println(s"Called for head $x and acc $acc")
			x + acc
		}) (0) (List(1,2,3,4,5))
		println(res)



	}
}
