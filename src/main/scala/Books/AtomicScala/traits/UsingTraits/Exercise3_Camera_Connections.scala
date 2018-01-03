package Books.AtomicScala.traits.UsingTraits



object Exercise3_Camera_Connections extends App {


     trait Connections {

          val maxConnections = 5
          var connected = 0

          def connect(connection: Boolean) = {
               connection match {
                    case true =>
                         if (connected < maxConnections){
                              connected = connected + 1
                              true
                         } else false
                    case false =>
                         if (connected > 0){
                              connected = connected - 1
                              true
                         } else false
               }
          }
     }




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


     val c = new WIFICamera with Connections
     assert(c.maxConnections == 5, "Test 1")
     assert(c.connect(true), "Test 2")
     assert(c.connected == 1, "Test 3")
     assert(c.connect(false), "Test 4")
     assert(c.connected == 0, "Test 5")
     assert(!c.connect(false), "Test 6")
}