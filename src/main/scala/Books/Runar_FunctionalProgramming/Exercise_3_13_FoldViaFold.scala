package Books.Runar_FunctionalProgramming

/**
  * foldRight in terms of reverse and foldLeft is a trick for avoiding
  * stack overflows
  *
  * These implementations aren't stack safe and won't work for large lists
  *
  * ** runar note in solutions
  */

object Exercise_3_13_FoldViaFold {

	def foldRightViaFoldLeft_1[A, B](list: List[A], seed: B)(f: (A, B) => B): B = {
		list.reverse.foldLeft(seed)((accB, a) => f(a, accB))
	}

	def foldRightViaFoldLeft_2[A, B](list: List[A], seed: B)(f: (A, B) => B): B = {

		val applyFoldR: B => B = list.foldLeft((seed: B) => seed) ((functionAccB, a) =>
			accB => functionAccB(f(a, accB)))

		applyFoldR(seed)
		//applying the seed because without the seed last, this expression returns a function
		//list.foldLeft((seed: B) => seed) ((gFunctionAccB, elemA) => accB => gFunctionAccB(f(elemA, accB))) (seed)
	}

	def foldLeftViaFoldRight[A, B](list: List[A], seed: B)(f: (B, A) => B): B ={

		val applyFoldL: B => B = list.foldRight((seed: B) => seed)( (a, functionAccB) =>
			accB => functionAccB(f(accB, a)))

		applyFoldL(seed)
	}


	// --------------------------------------------------------------------------------------------------

	def makeRightFoldUsingLeftFold[A, B](list: List[A]/*, seed: B*/)(f: (A, B) => B): B => B = {

		val applyFoldR: B => B = list.foldLeft((seed: B) => seed) ((functionAccB, a) =>
			accB => functionAccB(f(a, accB)))

		applyFoldR //(seed)
		//applying the seed because without the seed last, this expression returns a function
		//list.foldLeft((seed: B) => seed) ((gFunctionAccB, elemA) => accB => gFunctionAccB(f(elemA, accB))) (seed)
	}

	def makeLeftFoldUsingRightFold[A, B](list: List[A]/*, seed: B*/)(f: (B, A) => B): B => B ={

		val applyFoldL: B => B = list.foldRight((seed: B) => seed)( (a, functionAccB) =>
			accB => functionAccB(f(accB, a)))

		//applyFoldL(seed)
		applyFoldL
	}

	def main(args: Array[String]) {

		import org.scalatest.Assertions._

		val viewL: (String, Int) => String = (accStr: String, elem: Int) => s"($accStr + $elem)"
		val viewR: (Int, String) => String = (elem: Int, accStr: String) =>  s"($elem + $accStr)"

		val applyLeftFold: String => String = makeLeftFoldUsingRightFold((1 to 10).toList)(viewL)
		val applyRightFold: String => String = makeRightFoldUsingLeftFold((1 to 10).toList)(viewR)

		assert(applyLeftFold("0") == "((((((((((0 + 1) + 2) + 3) + 4) + 5) + 6) + 7) + 8) + 9) + 10)")
		assert(applyRightFold("0") == "(1 + (2 + (3 + (4 + (5 + (6 + (7 + (8 + (9 + (10 + 0))))))))))")
	}
}
