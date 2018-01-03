package Books.AtomicScala.classes.Polymorphism

import scala.reflect.runtime.{universe=>ru}
/**
  *
  */
object Exercise1_2_3_4_5_6_FantasyGame {


     object Name {
          def className(o: Any): String = {
               val currentMirror = ru.runtimeMirror(getClass.getClassLoader)
               currentMirror.reflect(o).symbol.toString.replace('$', ' ').split(' ').last
          }
     }

     trait Name {
          override def toString: String = Name.className(this)
     }


     class Element extends Name {
          //val id = getClass.getSimpleName.split('$').last
          def interact(other: Element) = s"$this interact $other"

          def draw: String = s"Drawing the $this"
     }

     //this.toString inherits from the conversion in Name reflection because both Inert and Wall
     // extend Element, which extends Name.
     class Inert extends Element { override def draw = s"$this drawing"}
     class Wall extends Inert { override def draw = s"Don't draw on the $this"}

     trait Material {
          def resilience: String
     }
     trait Wood extends Material{
          def resilience = "Breakable"
     }
     trait Rock extends Material {
          def resilience = "Hard"
     }

     class RockWall extends Wall with Rock
     class WoodWall extends Wall with Wood

     trait Skill
     trait Fighting extends Skill {
          def fight = "Fight!"
     }
     trait Digging extends Skill{
          def dig = "Dig!"
     }
     trait Magic extends Skill {
          def castSpell = "Spell!"
     }
     trait Flight extends Skill {
          def fly = "Fly!"
     }
     trait Swimmming extends Skill {
          def swim = "Swim!"
     }

     class Character(val player: String = "None") extends Element
     class Fairy extends Character with Magic
     class Unicorn extends Character with Magic with Flight
     class Mermaid extends Character with Magic with Swimmming
     class Dolphin extends Character with Swimmming
     class Viking extends Character with Fighting
     class Dwarf extends Character with Digging with Fighting
     class Wizard extends Character with Magic
     class Dragon(val dragonPlayer:String = "Puff") extends Character(dragonPlayer) with Magic with Flight
     class NoMagicNoFlyingDragon extends Character(player = "Puff")

     def battle(fighter: Fighting) = s"$fighter, ${fighter.fight}"

     def fly(flyer: Element with Flight, opponent: Element) = {
          s"$flyer, ${flyer.fly}, ${opponent.interact(flyer)}"
     }

     def swim(swimmer: Element with Swimmming, opponent: Element) = {
          s"$swimmer, ${swimmer.swim}, ${opponent.interact(swimmer)}"
     }

     def main(args: Array[String]) {

          val d = new Dragon
          //d.player = "Puff"
          assert(d.player == "Puff")
          assert(d.interact(new Wall) == "Dragon interact Wall")
          assert(d.interact(new RockWall) == "Dragon interact RockWall")
          assert(d.interact(new WoodWall) == "Dragon interact WoodWall")
          assert(d.interact(new Wizard) == "Dragon interact Wizard")

          val nd = new NoMagicNoFlyingDragon
          assert(nd.player == "Puff")

          assert(battle(new Viking) == "Viking, Fight!")
          assert(battle(new Dwarf) == "Dwarf, Fight!")
          // note anon is for anonymous since this class below is constructor anonymously.
          assert(battle(new Fairy with Fighting) == "anon, Fight!")
          assert(fly(new Unicorn, new Fairy) == "Unicorn, Fly!, Fairy interact Unicorn")
          assert(swim(new Mermaid, new Dolphin) == "Mermaid, Swim!, Dolphin interact Mermaid")

          Console.println((new Element).draw)
          Console.println((new Inert).draw)
          Console.println((new Wall).draw)
     }
}
