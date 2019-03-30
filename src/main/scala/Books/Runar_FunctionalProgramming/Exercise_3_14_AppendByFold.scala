package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_14_AppendByFold {
	// Appending two lists using fold
	def appendViaFoldLeft[A](list: List[A], other: List[A]): List[A] = {
		other.foldLeft(list)((accList, x) => accList ::: List(x))
	}

	def appendViaFoldRight[A](list: List[A], other: List[A]): List[A] = {
		list.foldRight(other)((x, accList) => x :: accList)
	}

	def main(args: Array[String]) {
		import org.scalatest.Assertions._

		val list = List(1,2,3,4)
		val other = List(5,6,7,8,9)

		assert(appendViaFoldLeft(list, other) === List(1, 2, 3, 4, 5, 6, 7, 8, 9))

		assert(appendViaFoldRight(list, other) === List(1, 2, 3, 4, 5, 6, 7, 8, 9))
	}
}
