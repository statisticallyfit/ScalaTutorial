package Eckel_AtomicScala.classes.CompanionObjects

import scala.math.round




object Exercise_3_4_WalkActivity extends App {

     class WalkActivity {
          def calories(lbs: Int, minutes: Int, mph: Double = 3): Long = {
               round((WalkActivity.MET(mph) * 3.5 * lbs * 0.45) / 200.0 * minutes)
          }
     }

     object WalkActivity {
          var activityLog = new String
          val MET = 2.3 // metabolic equivalent of the task

          def MET(mph: Double) = mph match {
               case x: Double if (x < 1.7) => 2.3
               case x: Double if (x < 2.5) => 2.9
               case x: Double if (x < 3) => 3.3
               case x: Double if (x >= 3) => 3.3
               case _ => 2.3
          }

          def start(athlete: String) = {
               activityLog += "\n[" + athlete + "]" + " Activity started!"
          }
          def stop(athlete: String) = {
               activityLog += "\n[" + athlete + "]" + " Activity stopped!"
          }
     }


     val sally = new WalkActivity
     assert(sally.calories(150, 30) == 117, "Test 1")
     println(sally.calories(150, 30))

     val john = new WalkActivity
     assert(john.calories(150, 30, 1.5) == 82, "Test 2")
     println(john.calories(150, 30, 1.5))
}