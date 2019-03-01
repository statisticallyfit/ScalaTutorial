package TypeclassesFromBlogs.VarianceTryOuts.CovarianceExamples


//note
/**
  * note: Source: http://blog.kamkor.me/Covariance-And-Contravariance-In-Scala/
  */



trait Bullet
class NormalBullet extends Bullet { override def toString = "NB"}
class ExplosiveBullet extends Bullet { override def toString = "EB"}


object AmmoMagazine {
     def newNormalBulletsMag = new AmmoMagazine(List.fill[NormalBullet](5)(new NormalBullet))
     def newExplosiveBulletsMag = new AmmoMagazine(List.fill[ExplosiveBullet](5)(new ExplosiveBullet))
}

/**
  *
  * Object private members can be accessed only from within the object in which they are defined.
  * It turns out that accesses to variables from the same object in which they are defined do not cause
  * problems with variance. The intuitive explanation is that, in order to construct a case where variance
  * would lead to type errors, you need to have a reference to a containing object that has a statically
  * weaker type than the type the object was defined with. For accesses to object private values, however,
  * this is impossible.
  * note conclusion: mutating state inside covariant class AmmoMagazine works because the state is made private.
  */

/*final*/ class AmmoMagazine[+A <: Bullet](private[this] var bullets: List[A]) {

     def hasBullets: Boolean = bullets.nonEmpty

     def giveNextBullet(): Option[A] ={
          bullets match {
               case Nil => None
               case t :: ts => {
                    bullets = ts
                    Some(t)
               }
          }
     }

     //def addSingleBullet[B >: A](b: A): Unit = bullets = bullets :+ b

     override def toString = s"AmmoMagazine(${bullets.mkString(", ")})"
}


/*final*/ case class Gun(private var ammoMag: AmmoMagazine[Bullet]) {

     def reload(ammoMag: AmmoMagazine[Bullet]): Unit ={
          this.ammoMag = ammoMag
     }

     def hasAmmo: Boolean = ammoMag.hasBullets

     //def addSingleBullet(bullet: Bullet) = ammoMag.addSingleBullet(bullet)

     /** Returns Bullet that was shot or None if there is ammo left */
     def shoot(): Option[Bullet] = ammoMag.giveNextBullet()
}


object GunRunner extends App {
     val gun = new Gun(AmmoMagazine.newNormalBulletsMag)
     println(gun.hasAmmo)
     println(gun)
     println(gun.shoot())
     println(gun)
     //works because of covariant subtyping
     gun.reload(AmmoMagazine.newExplosiveBulletsMag)
     println(gun.hasAmmo)
     println(gun)
     println(gun.shoot())
     println(gun)
     //gun.addSingleBullet(new ExplosiveBullet)
     //println(gun)
}
