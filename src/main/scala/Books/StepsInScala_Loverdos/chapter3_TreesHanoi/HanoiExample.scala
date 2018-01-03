package Books.StepsInScala_Loverdos.chapter3_TreesHanoi

/**
  * There are 3 poles and a tower of disks on the first pole, with the disks looking like
  * a pyramid as they are sorted in size from small to large.
  * Goal: move whole tower from first pole to second.
  * Rules:
  * 1. can use third pole as temporary placement
  * 2. can only move one disk at a time
  * 3. a larger disk cannot be placed atop a smaller one.
  *
  *
  * Problem reduction:
  * (i) key step: move the largest disk to pole B (hint: pole B must be empty just prior
  * to placing the largest disk there)
  * (ii) we cannot move the largest disk until all other disks atop it are removed.
  * Also, the other disks should not be moved to pole B since they block the largest disk's space.
  * Therefore, we should first move all other disks to pole C.
  * (iii) Then we can complete the key step of moving largest disk from A to B and then continuing
  * solving the problem.
  *
  * Reduced to:
  * 1) move the top (n-1) disks from A to C using B as temporary stop
  * 2) move the largest disk (Nth disk) from A to B
  * 3) move the (n-1) disks from C to B using A as temporary stop
  *
  */
object HanoiExample {

     object TowersOfHanoi {
          def solve(n:Int):List[String] ={
               def move(A:String, B:String) = List(A+B)
               def solver(n:Int, A:String, B:String, C:String):List[String] ={
                    if(n > 1){
                         solver(n - 1, A, C, B) ::: move(A, B) ::: solver(n-1, C, B, A)
                    } else List()
               }
               solver(n, "A", "B", "C")
          }
     }

     def main(args: Array[String]) {
          Console.println(TowersOfHanoi.solve(1) + "\n")
          Console.println(TowersOfHanoi.solve(2) + "\n")
          Console.println(TowersOfHanoi.solve(3) + "\n")
          Console.println(TowersOfHanoi.solve(4) + "\n")
          Console.println(TowersOfHanoi.solve(5) + "\n")
          Console.println(TowersOfHanoi.solve(10) + "\n")
     }
}
