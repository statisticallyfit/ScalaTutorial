package TEMP.AdHocPolyBlogTryOuts

/**
  *
  */
object TypeClass_GeneralWeapons {


     trait KitchenUtensil
     class FryingPan extends KitchenUtensil
     class WoodenBreadBoard extends KitchenUtensil
     // .. etc

     case class Creature(name: String, var health: Int) {
          def inflictDamage(severity: Int) = health -= severity
     }

     class Weapon(val damage: Int)
     case object Knife extends Weapon(10)
     case object Axe extends Weapon(25)


     // Being a library designer I want to allow other kinds of weapons
     // and they should all have a function which adapts them to be normal weapons
     trait GeneralWeapon[T] {
          def weapon: Weapon
     }

     // companion object provides implementation and implicit objects/vals
     object GeneralWeapon {
          implicit val FryingPanWeapon = new GeneralWeapon[FryingPan] {
               def weapon = new Weapon(9)
          }
          implicit val woodenBreadBoardWeapon = new GeneralWeapon[WoodenBreadBoard] {
               def weapon = new Weapon(1)
          }
     }


     object EpicBattle {
          def resolve(attackingWeapon: Weapon, targetCreature: Creature): Unit ={
               targetCreature.inflictDamage(attackingWeapon.damage)
          }
          // and I design my library with the extensible type
          def resolve[W: GeneralWeapon](attackingWeapon: W, targetCreature: Creature): Unit ={
               resolve(implicitly[GeneralWeapon[W]].weapon, targetCreature)
          }
     }


     def main (args: Array[String] ) {
          // the elf and orc fight in the woods with weapons
          val orc = Creature("orc", 80)
          val elf = Creature("Elf", 110)
          Console.println(s"Before $orc vs $elf")
          // the non-typeclass resolve method is used note (Ctrl-B)
          EpicBattle.resolve(Axe, elf)
          EpicBattle.resolve(Knife, orc)

          // the goblin and hobbit fight in the kitchen with utensils
          val goblin = Creature("goblin", 100)
          val hobbit = Creature("hobbit", 190)
          Console.println(s"Before $goblin vs $hobbit")
          // the typeclass resolve method is used note (Ctrl-B)
          EpicBattle.resolve(new FryingPan, goblin)
          EpicBattle.resolve(new WoodenBreadBoard, hobbit)


          Console.println(s"After $orc vs $elf")
          Console.println(s"After $goblin vs $hobbit")
     }
}
