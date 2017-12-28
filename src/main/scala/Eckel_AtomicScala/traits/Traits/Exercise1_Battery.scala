package Eckel_AtomicScala.traits.Traits


object Exercise1_Battery extends App {

     trait EnergySource

     trait BatteryPower{
          def monitor(charge: Int) = charge match {
               case c if c < 20 => "red"
               case c if c <= 39 => "yellow"
               case _ => "green"
          }
     }

     class Battery extends EnergySource with BatteryPower


     val battery = new Battery
     assert(battery.monitor(80) == "green")
     println(battery.monitor(80)) // green

     assert(battery.monitor(30) == "yellow")
     println(battery.monitor(30)) // yellow

     assert(battery.monitor(10) == "red")
     println(battery.monitor(10)) // red
}