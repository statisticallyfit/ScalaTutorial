package Loverdos_StepsInScala.chapter9_FilesForTraversable.path

/**
  *
  */
object Abstractions {

     trait Semigroup[A] {
          def compose(a:A, b:A): A
     }

     /** assume we have several predefined types of semigroups to be used implicitly
       * instead of passing them around as parameters.
       */
     //help why error?
     /*trait IamInASemigroup[A] {
          def composeWith(that: A)(implicit semi: Semigroup[A]) =
               semi.compose(this, that)
     }*/

     object PathSemigroup extends Semigroup[Path] {
          def compose(a: Path, b: Path) = Path.combine(a, b)
     }


     //paths hold the identity property of monoids: a . id = id . a
     // which example: p . EmptyPath = EmptyPath . p, so EmptyPath is the identity object
     trait Identity[+A] {
          def ident: A
     }

     trait Monoid[A] extends Semigroup[A] with Identity[A]

     object PathIdentity extends Identity[Path] {
          def ident = EmptyPath
     }

     object PathMonoid extends Monoid[Path] {
          def ident = PathIdentity.ident
          def compose(a: Path, b: Path) = Path.combine(a, b)
     }
}
