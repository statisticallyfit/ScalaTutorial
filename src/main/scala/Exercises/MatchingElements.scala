package Exercises

/**
  *
  */
object MatchingElements {

	 /**
	   * Given two lists, find all pairs of positions where the lists have
	   * elements in common. For "frogs" and "eggs", return
	   * List((3,1), (3,2), (4,3))
	   * @return List of (Int, Int)
	   */
	 def myMatchingElements[T](elemsA: List[T], elemsB: List[T]) ={
		  // Functional solution 1
		  val elemAIndexPairs: Seq[(T, Int)] = elemsA.zipWithIndex
		  val elemBIndexPairs: Seq[(T, Int)] = elemsB.zipWithIndex

		  // getting all the combinations of letter/index pairs for the two words,
		  // to have them accessible at the same  level
		  val allElemIndexPairs: Seq[(T, Int, T, Int)] = elemAIndexPairs.flatMap{
			   case (lA, iA) =>
					elemBIndexPairs.map{case (lB, iB) => (lA, iA, lB, iB)}
		  }

		  //filtering the pairs with equal letters, later to get their indices pairs
		  val equalElemsIndexPairs: Seq[(T, Int, T, Int)] =
			   allElemIndexPairs.filter{
					case (lA, iA, lB, iB) => lA == lB
			   }

		  //no just returning the indices, since the letters are the same
		  equalElemsIndexPairs.map{case(_, iA, _, iB) => (iA, iB)}.toList
	 }


	 def matchingElements[T](elemsA: List[T], elemsB: List[T]) ={
		  (for {
			   (a, ai) <- elemsA.zipWithIndex
			   (b, bi) <- elemsB.zipWithIndex if a == b
		  } yield ai -> bi).toList // the ai -> bi notation means tuple
	 }


	 def main(args: Array[String]) {

		  println(myMatchingElements("frogs".toList, "eggs".toList))

		  println(matchingElements("frogs".toList, "eggs".toList))
	 }
}
