/**
  * Created by joe on 04/05/17.
  */
object Euler35 extends App{

  val primes : Stream[Int] =
    2 #:: Stream.from(3).filter(n =>
      primes.takeWhile(p => p*p <= n)
        .forall(p => n%p!=0)
    )
  def rotations(n:Int):List[Int] = {
    val s = n.toString
    (0 until s.length).toList map (n => s.drop(n) + s.take(n)) map (_.toInt)
  }
  def isPrime(n:Int):Boolean = primes.takeWhile(p=>p*p<=n).forall(p=>n%p!=0)
  val ans = primes.takeWhile(_<(1000*1000))
    .count(p => rotations(p).forall(perm =>
      isPrime(perm))
    )
  println(ans)


}
