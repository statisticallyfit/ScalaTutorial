//package TEMP.AdHocPolyBlogTryOuts
//
////note  Source: http://gibbons.org.uk/scala-type-class
//
//
///**
//  * Scala Type Class
//  * It allows you to take a class and through ad-hoc polymorphism add operations
//  * which will work on that class without altering that class at all.  Also, leaving the
//  * type class open for extension by anyone in the future
//  *
//  * So, for my example, you have modeled some items in a kitchen.
//  * Then, some time in the future you decide that two creatures are fighting, and that
//  * they can pick up the kitchen stuff and use it as weapon.
//  *
//  * note: Normally, the kitchen classes have nothing about fighting in, so rather than couple
//  * note: them to creatures fighting, lets use a type class!
//  *
//  *
//  *
//  * todo --------- THE KEY POINTS ---------------------------------------------------------------------------
//  *
//  * --- Ad-hoc polymorphism - the Scala way to decouple classes. If you have classes in one domain
//  * which have nothing to do with a new set of operations in a different domain, use Type Classes.
//  *
//  * --- The type class simply defines the new operations in the new domain you want - a typed base trait, and
//  * some implicit objects or vals which Scala can infer from the types, providing they are in scope, and
//  * there is only one that matches.
//  *
//  * --- The companion object includes the initial implementations you want. Other devs can alter these or
//  * add more later on. The companion object effectively converts the classes you have already to extend the
//  * type class and provide the implementation.
//  *
//  * --- You define the objects and vals in the companion object to be implicit so they can be pulled in
//  * by the type.
//  *
//  * --- When using the type class, you should use a ‘Context bound type’ which is of the form
//  * def function[A:NameOfTypeClass], so we are saying that A is going to extend NameOfTypeClass,
//  * and that it will be pulled in from an implicit object or val which can convert it.
//  *
//  * --- implicitly[AdHocWeapon[ W2] ] is sadly the syntax to invoke the new operations (methods) in the
//  * type class implementation. eg implicitly[AdHocWeapon[W2] ].attack
//  *
//  *
//  *
//  */
//
//class FryingPan
//
//class WoodenBreadBoard
////... etc
//
//
//// Now for the fighting stuff, creatures have health and take damage.
//case class Creature(var name: String, var health: Int) {
//     def inflictDamage(severity: Int) = health -= severity
//}
//
//// 1. Define the type class - ie ad-hoc polymorphism
//trait AdHocWeapon[A] {
//     // 2. add some operations
//     def attack(weapon: A, target: Creature)
//}
//
//
//// 3. provide a companion object holding an object or a value with
//// some implementations.
//// Scala implicit resolution will find the object in scope which matches.
//// note: This companion shows an actual example of converting a
//// note: type into the operation attack.
//
//object AdHocWeapon {
//     // This is part of my library and others can add their own
//     // or override it later.
//     implicit object FryingPanAdHocWeapon extends AdHocWeapon[FryingPan] {
//          def attack(weapon: FryingPan, target: Creature) = target.inflictDamage(2000)
//     }
//
//     implicit object WoodenBreadBoardAdHocWeapon extends AdHocWeapon[WoodenBreadBoard] {
//          def attack(weapon: WoodenBreadBoard, target: Creature) = target.inflictDamage(2)
//     }
//}
//
//
//// 4. Code against the type class
//object FightInTheKitchen {
//
//     // note:   W: AdHocWeapon is a context bound, saying W will extend AdHocWeapon
//     def fightOneRound[W1: AdHocWeapon, W2: AdHocWeapon](weaponOfBeast1: W1, beast1: Creature,
//                                                         weaponOfBeast2: W2, beast2: Creature) = {
//          // 5. implicitly find the object with the function that
//          // matches the type.
//          implicitly[AdHocWeapon[W1]].attack(weaponOfBeast1, beast2)
//          implicitly[AdHocWeapon[W2]].attack(weaponOfBeast2, beast1)
//     }
//}
//
//
//
//// 6. Now I can fight using frying pans and breadboards - neither of which are weapons!
//object SimulationFight extends App {
//     val goblin = Creature("goblin", 10000)
//     val hobbit = Creature("hobbit", 10)
//     val wolf = Creature("wolf", 450)
//     val pixie = Creature("pixie", 7890)
//
//     Console.println(s"$goblin vs $hobbit")
//     FightInTheKitchen.fightOneRound(new FryingPan, goblin, new WoodenBreadBoard, hobbit)
//     Console.println(s"$goblin vs $hobbit after round 1")
//
//     //------
//     Console.println(s"$pixie vs $wolf")
//     FightInTheKitchen.fightOneRound(new WoodenBreadBoard, pixie, new FryingPan, wolf)
//     Console.println(s"$pixie vs $wolf after round 1")
//}
//
