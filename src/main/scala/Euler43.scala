import scala.collection.immutable

/**
  * Created by joe on 02/05/17.
  */
object Euler43 extends App{

  val primes:Stream[Int] = 2#:: Stream.from(3)
    .filter(n=>primes.takeWhile(p => p*p<=n).forall(p =>n%p!=0))
  val panDigits = (0 to 9).permutations.filter(_.head!=0)
  val triplets: Iterator[Int] = panDigits.map(_.tail.toList).map(makeTriplets).map(_.mkString("").toInt)
//  val tripsAndPrimes = triplets.map(_ zip primes)
//  val answers = tripsAndPrimes.filter{s => s.forall{case}}

  def makeTriplets(perm:List[Int]):Stream[(Int,Int,Int)] = perm match {
    case ys @ x1 :: x2 :: x3 :: xs => (x1,x2,x3) #:: makeTriplets(ys.tail)
    case _ => Stream.empty
  }
}
