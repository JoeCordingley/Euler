/**
  * Created by joe on 04/05/17.
  */
object Euler50 extends App{
  val a = 1000*1000

  val primes : Stream[Int] =
    2 #:: Stream.from(3).filter(n =>
      primes.takeWhile(p => p*p <= n)
      .forall(p => n%p!=0)
    )
  def isPrime(n:Int):Boolean = primes.takeWhile(p=>p*p<=n).forall(p=>n%p!=0)
//  val runs :Stream[Stream[Int]] = Stream(primes.head) #:: primes.tail.zip(runs).map{case (p,ps)=> p#::ps}
  def runningTotal(s:Stream[Int]):Stream[Int] = s.head #:: s.tail.zip(runningTotal(s)).map{case(a,b)=>a+b}
//  val answer = runs.map(runningTotal)
//    .takeWhile(_.head < a)
//    .map(_.takeWhile(_<=a))
//    .map(_.zipWithIndex)
//    .flatMap(_.filter{case (t,index)=> isPrime(t)})
//    .maxBy(_._2)
//    ._1
//  println(answer)
  val answer = Stream.from(0).map(n => primes drop n)
    .takeWhile(_.head < a)
    .map(runningTotal)
    .map(_.takeWhile(_<a))
    .flatMap(_.zipWithIndex)
    .filter{case (n,index) => isPrime(n)}
    .maxBy(_._2)
    ._1
  println(answer)

}
