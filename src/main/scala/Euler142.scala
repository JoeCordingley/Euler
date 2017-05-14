/**
  * Created by joe on 12/01/17.
  */
object Euler142 extends App{
  case class Yz(total:Long,y:Long,z:Long)

  def isPerfectSquare(i:Long):Boolean = {
    val root = Math.sqrt(i).toInt
    root*root==i
  }
  val pairs = for {
    total <- Stream.from(3)
    yMin = total/2 + 1
    yMax = total -1
    y <- yMin to yMax
    x = total - y
    if isPerfectSquare(y+x) && isPerfectSquare(y-x)
  } yield (x,y)

  val infiniteScroll = for {
    k <- Stream.from(3)
    j <- 2 until k
    i <- 1 until j
  } yield (i,j,k)


  val answer = {
    for {
      (i,j,k) <- infiniteScroll
      iPair = pairs(i)
      jPair = pairs(j)
      if iPair._1 == jPair._1
      kPair = pairs(k)
      if iPair._2 == kPair._1
      if jPair._2 == kPair._2
      total = iPair._1 + iPair._2 + jPair._2
    } yield total
  }.head

  println(answer)

}
