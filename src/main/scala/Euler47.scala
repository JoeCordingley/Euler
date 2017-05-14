object Euler47 {
  val primes: Stream[Int]  = 2#:: Stream.from(3).filter(n=> primes.takeWhile(p=> p*p <= n).forall(p => n%p!=0))
  def main(args:Array[String]):Unit = {
    println()
    def distinctPrimeFactors(n:Long):Int = primes.takeWhile(p => p*2 <= n).filter(p => n%p==0).size
    def distinctPrimeFactors2(n:Long) = primes.takeWhile(p => p*p <= n).filter(p => n%p==0).toList
    def find(first:Long,i:Long,amt:Int):Long = 
      if (distinctPrimeFactors(i)>= 4 && amt ==3) first
      else if (distinctPrimeFactors(i)>= 4 && amt == 0) find(i,i+1,1)
      else if (distinctPrimeFactors(i)>= 4) 
        find(first,i+1,amt+1)
      else find(0,i+1,0)
    println(find(0,2,0))
  }
}
