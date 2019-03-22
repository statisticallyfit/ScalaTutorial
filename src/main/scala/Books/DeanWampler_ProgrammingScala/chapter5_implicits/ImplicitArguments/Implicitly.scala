package Books.DeanWampler_ProgrammingScala.chapter5_implicits.ImplicitArguments


import math.Ordering

object Implicitly {

     case class MyList[A](list: List[A]){
          def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
               list.sortBy(f)(ord)

          def sortBy2[B: Ordering](f: A => B): List[A] =
               list.sortBy(f)(implicitly[Ordering[B]])
     }

     def main(args: Array[String]) {
          val list = MyList(List(1,3,5,2,4))
          Console.println(list sortBy1 (i => -i))
          Console.println(list sortBy2 (i => -i))
          assert((list sortBy1 (i => -i)) == List(5,4,3,2,1))
          assert((list sortBy2 (i => -i)) == List(5,4,3,2,1))
     }

}
