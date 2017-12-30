package Books.Eckel_AtomicScala.types.ExtensibleSystemsWithTypeClasses

/**
  *
  */
object Exercise6_Transformer {


     trait Transformer[F, T]{
          def convert(from: F): T
     }

     def transform[F, T](from: F)(implicit transformer: Transformer[F, T]): T ={
          transformer.convert(from)
     }

     def transformWrapper[F,T](from:F)(implicit transformer: Transformer[F, T]): String ={
          s"Converting ... $from to ${transform(from)}"
     }


     case class Cabin(location:String, woodSpecies:String, treesCut:Int)
     case class Tree(location:String, species:String, metersTall:Double){
          //note function to convert how many trees to build a cabin where the cabin's
          // length is equal to tree length (so 10 m tree makes 10x10 cabin)
          def calcTreesToCabin(): Int ={
               val wallLen = metersTall.toInt
               val treesPerWall = wallLen * 10
               val treesPerHouseSides = treesPerWall * 4
               val treesPerRoof = 2 * treesPerHouseSides
               val treesPerDoor = Math.round(treesPerWall / 4.0).toInt
               treesPerHouseSides + treesPerRoof + treesPerDoor
          }
     }
     case class Cheese(pieces:Int)
     case class Milk(buckets:Int){
          //note assume 1 bucket of milk makes 2 round pieces of cheese
          def calcMilkToCheese(): Int = buckets * 2
     }
     case class Fox(color:String, thickness:Int){
          def calcFoxToCoat(): Int = thickness match {
               case _ if thickness > 8 => 4
               case _ if thickness > 5 => 2
               case _ if thickness > 0 => 1
          }
          def calcExpensive(): Boolean = thickness match {
               case _ if thickness >= 8 => true
               case _ if thickness > 0 => false
          }
     }
     case class Deer(color: String, thickness:Int){
          def calcDeerToCoat(): Int = thickness match {
               case _ if thickness > 9 => 2
               case _ if thickness > 0 => 1
          }
          def calcExpensive(): Boolean = thickness match {
               case _ if thickness >= 9 => true
               case _ if thickness > 0 => false
          }
     }
     case class Coat(color:String, expensive:Boolean, animalsUsed:Int)



     implicit object Tree extends Transformer[Tree, Cabin] {
          def convert(tree:Tree): Cabin ={
               Cabin(tree.location, tree.species.capitalize, tree.calcTreesToCabin())
          }
     }

     implicit object Milk extends Transformer[Milk, Cheese] {
          def convert(milk:Milk): Cheese ={
               Cheese(milk.calcMilkToCheese())
          }
     }

     implicit object Fox extends Transformer[Fox, Coat] {
          def convert(fox:Fox): Coat ={
               Coat(fox.color, fox.calcExpensive(), fox.calcFoxToCoat())
          }
     }

     implicit object Deer extends Transformer[Deer, Coat]{
          def convert(deer:Deer): Coat ={
               Coat(deer.color, deer.calcExpensive(), deer.calcDeerToCoat())
          }
     }


     def main(args: Array[String]) {

          val objectsToTransform = List(
               Milk(4),
               Tree("Pennsylvania", "oak", 20.5),
               Tree("Switzerland", "pine", 30.78),
               Fox("orange-white", 8),
               Deer("golden-brown", 9),
               Fox("red", 5),
               Tree("Germany", "maple", 7.8),
               Milk(10),
               Deer("grey-brown", 3)
          )

          val result = objectsToTransform.map {
               case o:Milk => transformWrapper(o)
               case o:Tree => transformWrapper(o)
               case o:Fox => transformWrapper(o)
               case o:Deer => transformWrapper(o)
          }.mkString("\n")

          Console.println(result)
     }
}
