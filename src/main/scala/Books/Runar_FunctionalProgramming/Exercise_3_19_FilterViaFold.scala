package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_19_FilterViaFold {


	def filterViaFoldRight[A](list: List[A])(predicate: A => Boolean): List[A] ={
		list.foldLeft(List[A]())((accList, x) => if (predicate(x)) accList :+ x else accList )
	}

	def filterViaFoldLeft[A](list: List[A])(predicate: A => Boolean): List[A] ={
		list.foldRight(List[A]())((x, accList) => if(predicate(x)) x :: accList else accList )
	}

	def main(args: Array[String]) {

		import org.scalatest.Assertions._

		val list = List(4,-3,5,-9,6,7,1,-2,-3)

		assert(filterViaFoldLeft(list)(_ > 0) === list.filter(_ > 0))
		assert(filterViaFoldLeft(list)(_ > 0) === list.filter(_ > 0))

		assert(filterViaFoldRight(list)(_ % 2 == 0) === list.filter(_ % 2 == 0))
		assert(filterViaFoldRight(list)(_ % 2 == 0) === list.filter(_ % 2 == 0))
	}
}
