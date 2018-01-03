package Books.AtomicScala.traits.Traits


object Exercise2_Battery extends App {

     trait EnergySource

     trait BatteryPower{
          def monitor(charge: Int) = charge match {
               case c if c < 20 => "red"
               case c if c <= 39 => "yellow"
               case _ => "green"
          }
     }

     class Toy
     class Battery extends EnergySource with BatteryPower
     class BatteryPoweredToy extends Toy with BatteryPower


     val toy = new BatteryPoweredToy
     assert(toy.monitor(50) == "green")
     println(toy.monitor(50))
}