package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_21_FilterViaFlatMap {


	def filterViaFlatMap[A](list: List[A])(predicate: A => Boolean): List[A] ={
		list.flatMap(a => if(predicate(a)) List(a) else Nil)
	}

	def main(args: Array[String]) {
		import org.scalatest.Assertions._

		val nums = List(-8,5,4,2,-5,3,3,1,9)
		assert(filterViaFlatMap(nums)( _ > 3) === nums.filter( _ > 3))
		assert(filterViaFlatMap(nums)(_ < 0) === nums.filter(_ < 0))

		val words = List("dog", "hyperbole", "Hesperides", "dragon", "golden apple")
		assert(filterViaFlatMap(words)(_.length > 5) === words.filter(_.length > 5))
	}
}
