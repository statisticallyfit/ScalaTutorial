package ExercisesOnline

/**
  *
  */
object ElementHasDuplicates {

     //note the lement appears more than once
     def hasDuplicates1(data: Seq[Any], elem: Any): Boolean ={
          data.diff(data.distinct).count(_ == elem) != 0
     }

     def hasDuplicates2(data: Seq[Any], elem: Any): Boolean ={
          val dupMap = data
               .groupBy(x => identity(x))
               .collect { case ((k,vs)) => (k, vs.length)}
          dupMap.isDefinedAt(elem) match {
               case true => dupMap(elem) != 1
               case false => false
          }
     }

     def main(args: Array[String]) {
          val data = Array("a", "b", "a", "a", "e", "e", "f", "e",
               "g", "a", "b", "e", "g", "a", "a", "b", "g", "f", "z")

          assert(!hasDuplicates1(data, "z"))
          assert(hasDuplicates1(data, "a"))

          assert(!hasDuplicates2(data, "z"))
          assert(hasDuplicates2(data, "a"))
     }
}
