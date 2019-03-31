package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_16_AddOneViaFold {

	def addOneViaFoldLeft(list: List[Int]): List[Int] = {
		//seed can be stated as this or as List[Int]()
		list.foldLeft(Nil: List[Int])((accList, x) => accList :+ (x + 1))
	} //note: whatever you want to return you put as the seed = List!

	def addOneViaFoldRight(list: List[Int]): List[Int] ={
		list.foldRight(Nil: List[Int])((x, accList) => (x + 1) :: accList)
	}

	def main(args: Array[String]) {

		import org.scalatest.Assertions._

		val list: List[Int] = (0 until 8).toList

		assert(addOneViaFoldLeft(list) === list.map(_ + 1))
		assert(addOneViaFoldRight(list) === list.map(_ + 1))

	}
}
