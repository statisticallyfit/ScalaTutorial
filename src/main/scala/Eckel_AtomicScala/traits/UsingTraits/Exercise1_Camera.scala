package Eckel_AtomicScala.UsingTraits



object Exercise1_Camera extends App {


     trait WIFI {
          def reportStatus = "working"
          val address: String
     }


     class Camera{
          def showImage = "Showing video"
          def takeStillPhoto = "Took a picture"
     }

     class WIFICamera extends Camera with WIFI{
          val address = "192.168.0.200"
     }


     val webcam = new WIFICamera

     assert(webcam.showImage == "Showing video")
     println(webcam.showImage) // "Showing video"

     assert(webcam.address == "192.168.0.200")
     println(webcam.address)   // 192.168.0.200

     assert(webcam.reportStatus == "working")
     println(webcam.reportStatus) // "working"
}
