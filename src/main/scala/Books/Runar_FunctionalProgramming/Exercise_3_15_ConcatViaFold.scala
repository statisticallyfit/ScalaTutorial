package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_15_ConcatViaFold {

	//note: linear length time because of foldRight ??? pg 41

	import Exercise_3_14_AppendByFold._

	def concat[A](lists: List[List[A]]): List[A] ={
		lists.foldRight(List[A]())((newList, accAllLists) => appendViaFoldRight(newList, accAllLists))
	}


	def concat2[A](lists: List[List[A]]): List[A] ={
		lists.foldRight(List[A]())((newList, accAllLists) => newList ++ accAllLists)
	}

	def main(args: Array[String]) {

		import org.scalatest.Assertions._

		val l1 = List(1,2,3,4)
		val l2 = List(5,6,7,8,9)

		assert(concat(List(l1, l2)) === List(1,2,3,4,5,6,7,8,9))
		assert(concat2(List(l1, l2)) === List(1,2,3,4,5,6,7,8,9))
	}
}
