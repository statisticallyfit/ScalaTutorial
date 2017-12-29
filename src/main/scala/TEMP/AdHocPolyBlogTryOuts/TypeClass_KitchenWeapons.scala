//Source: http://gibbons.org.uk/scala-type-class


/**
  * Scala Type Class
  * It allows you to take a class and through ad-hoc polymorphism add operations
  * which will work on that class without altering that class at all.  Also, leaving the
  * type class open for extension by anyone in the future
  *
  * So, for my example, you have modeled some items in a kitchen.
  * Then, some time in the future you decide that two creatures are fighting, and that
  * they can pick up the kicthen stuff and use it as weapon.
  * Normally, the kitchen classes have nothing about fighting in, so rather than couple
  * them to creatures fighting, lets use a type class!
  */
class FryingPan {/* ... */}
class WoodenBreadBoard {/* ... */}
//... etc

// Now for the fighting stuff, creatures have health and take damage
case class Creature(var name:String, var health:Int) {
     def inflictDamange(severity:Int) = health -= severity
}

// 1. Define the type class - ie ad-hoc polymorphism using
trait AdHocWeapon[A] {
     // 2. add some operations you want for this thing
     def attack(weap: A, target:Creature)
}

// 3. provide a companion object holding an object or a val with some implementations
// Scala implicit resolution will find the one in scope which matches
// This companion shows actual example of converting a type into the operation attack.
object AdHocWeapon {
     // This is part of my library, and others can add their own or override it later
     implicit object FryingPanAdHocWeapon extends AdHocWeapon[FryingPan] {
          def attack(weap: FryingPan, target:Creature) = target.inflictDamange(20)
     }
     implicit object WoodenBreadBoardAdHocWeapon extends AdHocWeapon[WoodenBreadBoard] {
          def attack(weap: WoodenBreadBoard, target:Creature) = target.inflictDamange(2)
     }
}

// 4. Code against the type class
object FightInTheKitchen {
     // W:AdHocWeapon is context bound, saying W will extend AdHocWeapon
     def fightOneRound[W1:AdHocWeapon, W2:AdHocWeapon](beast2Weap:W1, beast1:Creature, beast1Weap:W2, beast2:Creature) = {
          // 5. implicitly find the  object with the function that matches the type
          implicitly[AdHocWeapon[W1]].attack(beast2Weap, beast1)
          implicitly[AdHocWeapon[W2]].attack(beast1Weap, beast2)
     }
}

// 6. Now I can fight using frying pans and breadboards neither of which are weapons.
object demoFight extends App {
     val vampire = Creature("Vampire", 10000)
     val hobbit = Creature("hobbit", 10)

     println(s"$vampire vs $hobbit")
     FightInTheKitchen.fightOneRound(new FryingPan, vampire,new WoodenBreadBoard, hobbit)
     println(s"$vampire vs $hobbit after round 1")
}