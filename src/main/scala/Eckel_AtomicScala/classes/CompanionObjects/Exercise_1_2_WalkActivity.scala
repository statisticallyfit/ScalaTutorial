package Eckel_AtomicScala.classes.CompanionObjects



object Exercise_1_2_WalkActivity extends App {

     class WalkActivity

     object WalkActivity {
          var activityLog = ""
          def start(athlete: String) = {
               activityLog += "\n[" + athlete + "]" + " Activity started!"
          }
          def stop(athlete: String) = {
               activityLog += "\n[" + athlete + "]" + " Activity stopped!"
          }
     }

     WalkActivity.start("Sally")
     WalkActivity.start("Brianna")
     WalkActivity.start("Madina")
     WalkActivity.start("Chanelle")

     WalkActivity.stop("Sally")
     WalkActivity.stop("Brianna")
     WalkActivity.stop("Madina")
     WalkActivity.stop("Chanelle")

     println(WalkActivity.activityLog)
}