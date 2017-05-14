/**
  * Created by joe on 02/05/17.
  */
object Euler41 extends App{
  val primes:Stream[Int] = 2#:: Stream.from(3)
    .filter(n=>primes.takeWhile(p => p*p<=n).forall(p =>n%p!=0))
  def isPrime(n:Int):Boolean = primes.takeWhile(p => p*p <= n).forall(p => n%p!=0)
  val answers = for {
    i <- (9 to 2 by -1).toStream
    perm <- (i to 1 by -1).permutations
    panDigit = perm.mkString("").toInt
    if isPrime(panDigit)
  } yield panDigit
  println(answers.head)

}
