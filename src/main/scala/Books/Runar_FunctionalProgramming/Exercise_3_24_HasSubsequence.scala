package Books.Runar_FunctionalProgramming

/**
  *
  */
object Exercise_3_24_HasSubsequence {

	def hasSubsequence[A](list: List[A], sub: List[A]): Boolean ={
		(list, sub) match {
			case (_, Nil) => true
			case (Nil, _) => sub == Nil //false
			case (x :: xs, y :: ys) if x == y => hasSubsequence(xs, ys)
			case (x :: xs, y :: ys) if x != y => hasSubsequence(xs, y :: ys)
		}
	}

	object Solution {
		def startsWith[A](list: List[A], prefix: List[A]): Boolean = {
			(list, prefix) match {
				case (_, Nil) => true
				case (x :: xs, y :: ys) if x == y => startsWith(xs, ys)
				case _ => false
			}
		}

		def hasSubsequence[A](list: List[A], sub: List[A]): Boolean = {
			list match {
				case Nil => sub == Nil
				case _ :: xs => hasSubsequence(xs, sub)
				case _ => startsWith(list, sub)
			}
		}
	}

	def main(args: Array[String]) {
		import org.scalatest.Assertions._

		object Util {
			def inOrderSubsets[A](list: List[A]): List[List[A]] = {
				(1 to list.length).toList.flatMap(len => list.sliding(len).toList)
			}

			def powerSet[A](list: List[A]): List[List[A]] ={
				//val slideViews = (1 to list.length).toList.flatMap(len => list.sliding(len).toList)
				//slideViews.flatMap(s => s.permutations.toList)
				List() +: (1 to list.length).toList.flatMap(len => list.combinations(len).toList)
			}
		}

		val testerList = List(4,23, 33, 7,8,-9,-5,1,2,-17,142)

		// ---------------------------------------------------------------------------------------------------
		//Testing - preliminary logic. It must be true that the inordersubsets of all lengths are contained in the
		// powerset
		val testerInorderSubsets = Util.inOrderSubsets(testerList)
		val testerPowerSets = Util.powerSet(testerList)

		assert(testerInorderSubsets.length < testerPowerSets.length, "Logic 1")
		assert(testerInorderSubsets.intersect(testerPowerSets) === testerInorderSubsets, "Logic 2")

		// ---------------------------------------------------------------------------------------------------
		//The in-order test - this must pass
		assert(testerInorderSubsets.forall(sub => hasSubsequence(testerList, sub)))
		//todo - weird, the solution doesn't return all possible in order subsets
		//assert(testerInorderSubsets.forall(sub => Solution.hasSubsequence(testerList, sub)), "Sol 1")

		// ---------------------------------------------------------------------------------------------------
		//Some obvious false tests - these subsets are not there, in any order
		assert(! hasSubsequence(testerList, List(16,7))) /// simply not there

		// ---------------------------------------------------------------------------------------------------
		// Testing powersets is all subsequences

		assert(testerPowerSets.forall(pset => hasSubsequence(testerList, pset)))
		////todo - weird, the solution doesn't return all possible in order subsets
		//assert(testerPowerSets.forall(pset => Solution.hasSubsequence(testerList, pset)), "Sol 2")

		//special case of power set tested here
		assert(hasSubsequence(testerList, List(4, 33))) // skipping allowed
	}
}
