package Books.Runar_FunctionalProgramming

import scala.annotation.tailrec

/**
  *
  */
object Exercise_3_BinaryTree {


	sealed trait Tree[+A] {
		//def isEmpty
	}
	//case class Empty[A]() extends Tree[A]
	case object Empty extends Tree[Nothing]
	case class Leaf[A](value: A) extends Tree[A]
	case class Node[A](a: A, left: Tree[A], right: Tree[A]) extends Tree[A]

	def isEmpty[A](tree: Tree[A]) = tree match {
		case Empty => true
		case _ => false
	}

	object Tree {

		//exercise 3.25

		object Folds {

			//fold right in order
			def foldRIn[A, B](seed: B)(f: (A, B) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => seed
					case Leaf(n) => f(n, seed)
					case Node(n, left, right) => {
						val rightAcc: B = foldRIn(seed)(f)(right)
						val currentAcc: B = f(n, rightAcc)
						val leftAcc: B = foldRIn(currentAcc)(f)(left)
						leftAcc
					}
				}
			}

			//fold left INORDER
			def foldLIn[A, B](seed: B)(f: (B, A) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => seed
					case Leaf(n) => f(seed, n)
					case Node(n, left, right) => {
						val rightAcc: B = foldLIn(seed)(f)(right)
						val currentAcc: B = f(rightAcc, n)
						val leftAcc: B = foldLIn(currentAcc)(f)(left)
						leftAcc
					}
				}
			}

			//fold right POSTORDER
			def foldRPost[A, B](seed: B)(f: (A, B) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => seed
					case Leaf(n) => f(n, seed)
					case Node(n, left, right) => {
						val currentAcc: B = f(n, seed)
						val rightAcc: B = foldRPost(currentAcc)(f)(right)
						val leftAcc: B = foldRPost(rightAcc)(f)(left)
						leftAcc
					}
				}
			}
			//fold left POSTORDER
			def foldLPost[A, B](seed: B)(f: (B, A) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => seed
					case Leaf(n) => f(seed, n)
					case Node(n, left, right) => {
						val currentAcc: B = f(seed, n)
						val rightAcc: B = foldLPost(currentAcc)(f)(right)
						val leftAcc: B = foldLPost(rightAcc)(f)(left)
						leftAcc
					}
				}
			}

			// fold right PREORDER
			def foldRPre[A, B](seed: B)(f: (A, B) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => seed
					case Leaf(n) => f(n, seed)
					case Node(n, left, right) => {
						val currentAcc: B = f(n, seed)
						val leftAcc: B = foldRPre(currentAcc)(f)(left)
						val rightAcc: B = foldRPre(leftAcc)(f)(right)
						rightAcc
					}
				}
			}

			// fold left PREORDER
			def foldLPre[A, B](seed: B)(f: (B, A) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => seed
					case Leaf(n) => f(seed, n)
					case Node(n, left, right) => {
						val currentAcc: B = f(seed, n)
						val leftAcc: B = foldLPre(currentAcc)(f)(left)
						val rightAcc: B = foldLPre(leftAcc)(f)(right)
						rightAcc
					}
				}
			}

			//fold with function of more arguments (f takes args on either side)
			def fold[A, B](seed: B)(f: (A, B, B) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => seed
					case Leaf(n) => f(n, seed, seed) //todo help how to fill in the rest of the arguments?
					case Node(n, left, right) => f (n, fold(seed)(f)(left) , fold(seed)(f)(right))
				}
			}

			//help this definition won't work because there is no case for Empty
			/*def fold2[A, B](f: A => B)(g: (B, B) => B)(tree: Tree[A]): B = {
				tree match {
					case Empty => _
					case Leaf(n) => f(n)
					case Node(n, leftTree, rightTree) => g( fold2(f)(g)(leftTree), fold2(f)(g)(rightTree) )
				}
			}*/
		}


		//sides counts the number of ndoes
		object Size {
			import Folds._
			def size[A](tree: Tree[A]): Int ={
				tree match {
					case Empty => 	0
					case Leaf(_) => 1
					case Node(_, left, right) => 1 + size(left) + size(right)
				}
			}
			def sizeViaFoldLIn[A](tree: Tree[A]): Int = {
				foldLIn(0)((accSize: Int, n: A) => 1 + accSize)(tree)
			}

			def sizeViaFoldRIn[A](tree: Tree[A]): Int = {
				foldRIn(0)((n: A, accSize: Int) => 1 + accSize)(tree)
			}

			def sizeViaFold[A](tree: Tree[A]): Int = {
				//note: this has seed = 1
				fold(1)((n: A, leftAccSize: Int, rightAccSize: Int) => 1 + leftAccSize + rightAccSize)(tree)
			}

			//todo
			/*def sizeViaFold2[A](tree: Tree[A]): Int = {
				fold2( (a: A) => 1)((leftSize: Int, rightSize: Int) => 1 + leftSize + rightSize)(tree)
			}*/
		}

		object Maximum {
			import Folds._

			def maximum(tree: Tree[Int]): Int = {
				tree match {
					case Empty => Int.MinValue //todo is there a better way?
					case Leaf(n) => n
					case Node(nodeValue, left, right) =>
						maximum(left) max maximum(right) max nodeValue
				}
			}
			def maximumViaFoldLIn(tree: Tree[Int]): Int = {
				foldLIn(0)((accMax: Int, n: Int) => accMax max n)(tree)
			}

			def maximumViaFoldRIn(tree: Tree[Int]): Int = {
				foldRIn(0)((n: Int, accMax: Int) => accMax max n)(tree)
			}

			def maximumViaFold(tree: Tree[Int]): Int = {
				fold(0)((n: Int, leftMax: Int, rightMax: Int) => n max leftMax max rightMax)(tree)
			}

			/*def maximumViaFold2(tree: Tree[Int]): Int = {
				fold2((a: Int) => a)((leftMax: Int, rightMax: Int) => leftMax max rightMax)(tree)
			}*/
		}


		//todo: continue
		// https://github.com/statisticallyfit/HaskellTutorial/blob/5b7fd00869cd65bd0b59b43f8c2969509bde2485/learningprojects/TreeProject/src/BinaryTree/BinaryTreeOps.hs
		//todo https://github.com/statisticallyfit/HaskellTutorial/blob/5b7fd00869cd65bd0b59b43f8c2969509bde2485/learninghaskell/src/Books/ChrisAllen_HaskellFirstPrinciples/chapter11_AlgebraicDataTypes/AlgebraicDataTypes.hs
		//todo https://github.com/statisticallyfit/HaskellTutorial/blob/5b7fd00869cd65bd0b59b43f8c2969509bde2485/learninghaskell/src/Books/ChrisAllen_HaskellFirstPrinciples/chapter21_Traversable/chapterExercises/exercise_Tree.hs
		//todo https://github.com/statisticallyfit/HaskellTutorial/blob/5b7fd00869cd65bd0b59b43f8c2969509bde2485/learninghaskell/src/Books/SimonThompson_Craft/chapter16/exercises/exercise16.29.hs



		object Depth {
			import Folds._

			def depth[A](tree: Tree[A]): Int = {
				tree match {
					case Empty => 	0
					case Leaf(_) => 0
					case Node(_, left, right) => 1 + (depth(left) max depth(right))
				}
			}

			//not possible, need the triple-argument fold at the bottom to be able to compare sides
			/*def depthViaFoldLIn[A](tree: Tree[A]): Int = {
				foldLIn(0)((accDepth: Int, _: A) => 1 + accDepth)(tree)
			}*/
			//TODO help why is this returning 4, not 3 for the tree?
			def depthViaFold[A](tree: Tree[A]): Int = {
				fold(0)((_: A, leftDepth: Int, rightDepth: Int) => 1 + (leftDepth max rightDepth))(tree)
			}

			/*def depthViaFold2[A](tree: Tree[A]): Int = {
				fold2((a: A) => 0)((leftDepth, rightDepth) => 1 + (leftDepth max rightDepth))(tree)
			}*/
		}

		object Map {
			import Folds._

			def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
				tree match {
					case Empty => Empty
					case Leaf(n) => Leaf(f(n))
					case Node(n, left, right) => Node(f(n), map(left)(f), map(right)(f))
				}
			}

			def mapViaFold[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
				fold(Empty:Tree[B])((n: A, left: Tree[B], right: Tree[B]) =>
					if(isEmpty(left) && isEmpty(right)) Leaf(f(n))
					else Node(f(n), left, right))(tree)
			}

			/*def fold[A, B](seed: B)(f: (A, B, B) => B)(tree: Tree[A]): B = {
				tree match {
					case Leaf(n) => f(n, seed, seed) //todo help how to fill in the rest of the arguments?
					case Node(n, left, right) => f (n, fold(seed)(f)(left) , fold(seed)(f)(right))
				}
			}*/

			/*def mapViaFold[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
				fold2[A, B]
				/*fold(Tree[B]())(
					(n: A, leftAcc: B, rightAcc: B) => Node(f(n), Leaf(leftAcc), Leaf(rightAcc)))(tree)*/
			}*/

			//HELP TODO
			/*sealed trait Another[+A]
			case class Petal[A](n: A) extends Another[A]
			case class Branch[A](left: Another[A], right: Another[A]) extends Another[A]

			def foldT[A, B](f: A => B)(g: (B, B) => B)(tree: Another[A]): B = {
				tree match {
					case Petal(n) => f(n)
					case Branch(leftTree, rightTree) => g( foldT(f)(g)(leftTree), foldT(f)(g)(rightTree) )
				}
			}

			def fMap[A, B](tree: Another[A])(f: A => B): Another[B] =
				foldT((x: A) => Petal(f(x)): Another[B])((x: Another[B], y: Another[B]) => Branch(x, y))(tree)*/

			//help
			/*def mapViaFold2[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
				myFold2((a: A) => Leaf(f(a)): Tree[B]) ((n: Tree[B], leftAcc: Tree[B], rightAcc: Tree[B]) =>
					Node(f(n), leftAcc, rightAcc))(tree)
			}*/


			/*def myFold2[A, B](f: A => B)(g: (B, B, B) => B)(tree: Tree[A]): B = {
				tree match {
					case Leaf(n) => f(n)
					case Node(n, leftTree, rightTree) =>
						g(f(n), myFold2(f)(g)(leftTree), myFold2(f)(g)(rightTree) )
				}
			}*/
		}
	}


	def main(args: Array[String]) {
		import org.scalatest.Assertions._

		import Tree.Size._
		import Tree.Depth._
		import Tree.Folds._
		import Tree.Map._
		import Tree.Maximum._

		//val t: Tree[Int] = Node(1, Node(3, Node(10, Leaf(8), Leaf(2)), Leaf(4)), Node(2, Leaf(1), Leaf(-3)))
		val t: Tree[Int] = Node(1, Node(3, Node(10, Node(0, Leaf(8), Empty), Leaf(2)), Leaf(4)), Node(2, Leaf(1),
			Node(0, Empty, Leaf(-3))))


		assert(size(t) == 11, "Test 1")
		assert(sizeViaFoldLIn(t) == 11, "Test 2")
		assert(sizeViaFoldRIn(t) == 11, "Test 3")
		//todo not working since changed Tree //assert(sizeViaFold(t) == 11, "Test 4")

		assert(maximum(t) == 10, "Test 5")
		assert(maximumViaFoldLIn(t) == 10, "Test 6")
		assert(maximumViaFoldRIn(t) == 10, "Test 7")
		assert(maximumViaFold(t) == 10, "Test 8")

		assert(depth(t) == 4, "Test 9")
		//todo this always returns one more?? //assert(depthViaFold(t) == 4, "Test 10")

		val treeMultipliedFive: Tree[Int] = Node(5, Node(15, Node(50, Node(0, Leaf(40), Empty), Leaf(10)), Leaf(20)),
			Node(10, Leaf(5), Node(0, Empty, Leaf(-15))))

		assert(map(t)(_ * 5) === treeMultipliedFive, "Test 11")
		assert(mapViaFold(t)(_ * 5) === treeMultipliedFive, "Test 12")


		// Testing folds now
		//First some sanity checks - all the methods give the same sum ---------------------------------
		assert(foldLIn(0)((x: Int, y: Int) => x + y)(t) == 28, "Test 13")
		assert(foldRIn(0)((x: Int, y: Int) => x + y)(t) == 28, "Test 14")

		assert(foldLPost(0)((x: Int, y: Int) => x + y)(t) == 28, "Test 15")
		assert(foldRPost(0)((x: Int, y: Int) => x + y)(t) == 28, "Test 16")

		assert(foldLPre(0)((x: Int, y: Int) => x + y)(t) == 28, "Test 17")
		assert(foldRPre(0)((x: Int, y: Int) => x + y)(t) == 28, "Test 18")

		assert(fold(0)((x: Int, y: Int, z: Int) => x + y + z)(t) == 28, "Test 19")


		//Now test their differences - they difference differently ---------------------------------
		//TODO: do arbitrary instances and check if always true that foldlef_inorder == foldleft_postorder ? etc
		assert(foldLIn(0)((x: Int, y: Int) => x - y)(t) == -28, "Test 20")
		/*assert(foldRIn(0)((x: Int, y: Int) => x - y)(t) == -4, "Test 21")

		assert(foldLPost(0)((x: Int, y: Int) => x - y)(t) == -28, "Test 22")
		assert(foldRPost(0)((x: Int, y: Int) => x - y)(t) == 10, "Test 23")

		assert(foldLPre(0)((x: Int, y: Int) => x - y)(t) == -28, "Test 24")
		assert(foldRPre(0)((x: Int, y: Int) => x - y)(t) == -4, "Test 25")

		assert(fold(0)((x: Int, y: Int, z: Int) => x - y - z)(t) == -2, "Test 26")*/
	}
}
