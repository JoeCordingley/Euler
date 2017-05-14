import scala.collection.mutable

/**
  * Created by joe on 29/03/17.
  */
object Euler12 extends App{

  val triangles = Stream.from(1).map(n => n*(n+1)/2)
  val primefactors =  mutable.Map.empty[Int,List[Int]]
  val factors :Stream[List[Int]] = Nil #:: Nil #:: Stream.from(2).map{ n =>
    val p = primes.dropWhile(p=> n%p!=0).head
      p :: factors(n/p)
  }
  val primes:Stream[Int] = 2 #:: Stream.from(3).filter(n => primes.takeWhile(p=>p*p<=n).forall(p => n%p!=0))

  def noOfDivisors[A](l:List[A]):Int = {
    for {
      s <- 1 to l.size
      c <- l.combinations(s)
    } yield ()
  }.size + 1

  def primeDivisors(n:Int):List[Int] = n match {
    case 1 => Nil
    case 0 => Nil
    case _ => {
      val p = primes.dropWhile(p=> n%p!=0).head
      p :: primefactors.getOrElseUpdate(n/p,primeDivisors(n/p))
    }
  }
  println(triangles.dropWhile(t => noOfDivisors(primeDivisors(t))<=500).head)


}
