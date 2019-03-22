package Exercises

/**
  *
  */
object Prime {

	 def prime(number: Int) ={
		  number > 1 && !(2 until number).exists(number % _ == 0)
	 }


	 def main(args: Array[String]) {
		  println((1 to 100).filter(prime))
		  println((1 to 100).filter(prime).map(identity))
	 }
}
