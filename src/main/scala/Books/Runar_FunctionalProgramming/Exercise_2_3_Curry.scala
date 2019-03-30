package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_2_3_Curry {

	//example partial
	//note 1: f takes an a and b and the only way to get the result C
	// note is to pass the a and b to f
	def partial[A, B, C](a: A, f:(A, B) => C): B => C ={
		(b: B) => f(a, b)
			//results in higher order function that takes a function of two
			//arguments and partially applies it
			//We place in the A and now the resulting function just needs
			// the B to result in the C

	}

	//or use Function2/s curried method
	def curry[A, B, C](f: (A, B) => C): A => (B => C) ={

		(a: A) => ((b: B) => f(a, b))
			//a => b => f(a, b)
	}

	//uncurry from Function2
	def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
		(a, b) => f(a)(b)
	}

	def main(args: Array[String]) {

		import org.scalatest.Assertions._

		//TODO define examples
		//val addBy3: Int => Int = partial(a, (a, b) => a + b)

		case class Banana(length: Int, color: String)

		//def bananaLengthAdder(increment: Int) = partial(increment, (_, color) => Banana(increment, color))

		def uncurriedBanana: (Int, String) => Banana = (len: Int, col: String) => Banana(len, col)

		def curriedBanana: Int => String => Banana = curry(uncurriedBanana)

		assert(uncurriedBanana(10, "yellow") === Banana(10, "yellow"))
		assert(curriedBanana(15)("brown") === Banana(15, "brown"))
	}
}
