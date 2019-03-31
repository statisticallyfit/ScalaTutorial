package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_18_MapViaFold {

	/**
	  * def addOneViaFoldLeft(list: List[Int]): List[Int] = {
	  * //seed can be stated as this or as List[Int]()
	  * 		list.foldLeft(Nil: List[Int])((accList, x) => accList :+ (x + 1))
	  * } //note: whatever you want to return you put as the seed = List!
	  *
	  * def addOneViaFoldRight(list: List[Int]): List[Int] ={
	  * 		list.foldRight(Nil: List[Int])((x, accList) => (x + 1) :: accList)
	  * }
	  */

	def mapViaFoldLeft[A, B](list: List[A])(f: A => B): List[B] = {
		list.foldLeft(Nil: List[B])((accList, x) => accList :+ f(x))
	}

	def mapViaFoldRight[A, B](list: List[A])(f: A => B): List[B] = {
		list.foldRight(Nil: List[B])((x, accList) => f(x) :: accList)
	}

	def main(args: Array[String]) {
		import org.scalatest.Assertions._

		val list = (0 until 10).toList

		assert(mapViaFoldLeft(list)( _ * 8) === list.map(_ * 8))
		assert(mapViaFoldRight(list)( _ * 8) === list.map(_ * 8))

		assert(mapViaFoldLeft(list)( _ + 4) === list.map(_ + 4))
		assert(mapViaFoldRight(list)( _ + 4) === list.map(_ + 4))
	}
}
