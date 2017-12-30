package Books.Horstmann_ScalaImpatient.chapter4_mapsAndTuples

import scala.collection.JavaConversions._

/**
  *
  */
object Exercise7_MapProperties {

     def main(args: Array[String]) {

          val props = propertiesAsScalaMap(System.getProperties)
          val maxLengthOfKey = props.keySet.map(_.length).max

          for( (k, v) <- props)
               printf("%-" + maxLengthOfKey + "s | %s\n", k, v)
          //todo: what does the "s" in "s | " mean?
     }
}
