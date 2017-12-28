package Eckel_AtomicScala.traits.Traits



object Exercise3_Battery extends App {

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

     val toy2 = new Toy with BatteryPower
     assert(toy2.monitor(50) == "green")
     println(toy2.monitor(50)) //green
}