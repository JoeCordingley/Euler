/**
  * Created by joe on 02/04/17.
  */
object Euler16 extends App{

  def power(n:Int,i:Int):BigInt = {
    def go(acc:BigInt,i:Int):BigInt = if (i == 0) acc else go(acc*n,i-1)
    go(1,i)
  }
  val answer = power(2,1000).toString.map(_.asDigit).sum
  println(answer)

}
