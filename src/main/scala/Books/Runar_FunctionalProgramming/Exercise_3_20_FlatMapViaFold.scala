package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_20_FlatMapViaFold {


	def flatMapViaFoldLeft[A, B](list: List[A])(f: A => List[B]): List[B] ={
		list.foldLeft(List[B]())((accList, a) => accList ++ f(a))
	}

	def flatMapViaFoldRight[A, B](list: List[A])(f: A => List[B]): List[B] ={
		list.foldRight(List[B]())((a, accList) => f(a) ++ accList)
	}

	def main(args: Array[String]) {
		import org.scalatest.Assertions._

		val list = List(1,2,3,4,5)

		assert(flatMapViaFoldLeft(list)(a => List(a + 1)) === list.map(_ + 1))
		assert(flatMapViaFoldRight(list)(a => List(a + 1)) === list.map(_ + 1))

		assert(flatMapViaFoldLeft(list)(a => List(a, a, a)) === list.flatMap(a => List(a,a,a)))
		assert(flatMapViaFoldRight(list)(a => List(a, a, a)) === list.flatMap(a => List(a,a,a)))
	}
}
