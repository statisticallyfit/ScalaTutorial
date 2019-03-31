package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_9_LengthViaFold {

	def lengthViaFoldLeft[A](list: List[A]): Int = {
		list.foldLeft(0)((accCounter, _) => accCounter + 1)
	}

	def lengthViaFoldRight[A](list: List[A]): Int = {
		list.foldRight(0)((_, accCounter) => accCounter + 1)
	}

	def main(args: Array[String]) {

		import org.scalatest.Assertions._

		assert(lengthViaFoldLeft((0 until 20).toList) === 20)
		assert(lengthViaFoldLeft(List(1)) === 1)
		assert(lengthViaFoldLeft(List()) === 0)

		assert(lengthViaFoldRight((0 until 20).toList) === 20)
		assert(lengthViaFoldRight(List(1)) === 1)
		assert(lengthViaFoldRight(List()) === 0)
	}
}
