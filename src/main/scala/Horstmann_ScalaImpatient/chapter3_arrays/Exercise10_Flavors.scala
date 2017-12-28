package Horstmann_ScalaImpatient.chapter3_arrays

import java.awt.datatransfer.{DataFlavor, SystemFlavorMap}

/**
  *
  */
object Exercise10_Flavors {

     def main(args: Array[String]) {
          val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
          println(flavors.getNativesForFlavor(DataFlavor.imageFlavor))
     }
}
