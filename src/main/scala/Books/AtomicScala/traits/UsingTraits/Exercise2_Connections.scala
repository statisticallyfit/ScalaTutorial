package Books.AtomicScala.traits.UsingTraits



object Exercise2_Connections extends App {


     trait Connections {

          val maxConnections = 5
          var connected = 0

          def connect(connection: Boolean) = {
               connection match {
                    case true =>
                         if (connected < maxConnections) {
                              connected = connected + 1
                              true
                         } else false
                    case false =>
                         if (connected > 0) {
                              connected = connected - 1
                              true
                         } else false
               }
          }
     }


     val c = new Object with Connections

     assert(c.maxConnections == 5, "Test 1")
     assert(c.connect(true), "Test 2")
     assert(c.connected == 1, "Test 3")

     for (i <- 0 to 3)
          assert(c.connect(true), "Test 4-" + i)
     assert(!c.connect(true), "Test 5")
     assert(c.connect(false), "Test 6")
     assert(c.connected == 4, "Test 7")

     for (i <- 0 to 3)
          assert(c.connect(false), "Test 8-" + i)
     assert(c.connected == 0, "Test 9")
     assert(!c.connect(false), "Test 10")
}