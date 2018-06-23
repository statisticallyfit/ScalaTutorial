/*
package TEMP.ShapelessTryOuts

import shapeless._

/**
  *
  */


trait SetVecLike[V, F] {
     def rref(vset: V): V
}

trait MatrixLike[M, F] extends SetVecLike[M, F] {
     def adjoint(m: M): M
}


trait Number[N] {
     def plus(a: N, b: N): N
}

class SetOfVectors[N: Number](cols: List[N]*)

object SetVecLike {

     implicit def vsetLikeForSetOfVectorsHList[N: Number]: SetVecLike[SetOfVectors[N] :: HNil, N] = new
               SetVecLike[SetOfVectors[N] :: HNil, N]{

          override def rref(vset: SetOfVectors[N] :: HNil): SetOfVectors[N] :: HNil = new SetOfVectors[N]()
     }
}

trait VectorDSL {

}
*/
