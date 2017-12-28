package Horstmann_ScalaImpatient.chapter4_mapsAndTuples

/**
  *
  */
object Exercise10_CaseConverterMap {

     def alphabetToLowercase() = {
          "ABCDEFGHIJKLMNOPQRSTUVWXYZ".zip("abcdefghijklmnopqrstuvwxyz").toMap
     }

     def main(args: Array[String]) {
          val caseMap: Map[Char, Char] = alphabetToLowercase()
          val lower = caseMap('N')
          println(lower)
     }
}
