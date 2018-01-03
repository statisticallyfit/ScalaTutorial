package Books.AtomicScala.types.ALittleReflection

import scala.reflect.runtime.currentMirror
//import scala.reflect.runtime.{universe=>ru}


object NameExample {

     object Name {
          def className(o: Any): String = {
               //val currentMirror = ru.runtimeMirror(getClass.getClassLoader)
               currentMirror.reflect(o).symbol.toString.replace('$', ' ').split(' ').last
          }
     }

     trait Name {
          override def toString: String = Name.className(this)
     }


     class Solid extends Name
     class AnotherSolid(val size:Int) extends Name {
          override def toString = s"${super.toString}($size)"
     }

     def main(args: Array[String]) {
          val s = new Solid
          val as = new AnotherSolid(47)
          assert(s.toString == "Solid")
          assert(as.toString == "AnotherSolid(47)")
     }

}
