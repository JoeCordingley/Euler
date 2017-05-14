/**
  * Created by joe on 22/03/17.
  */
object Euler223 extends App {

  val max = 25*1000*1000
  val aMax = max/3
  def find(a:Int,initialB:Int): Set[(Int,Int,Int)] = {
    def go(b:Int,acc:Set[(Int,Int,Int)]):Set[(Int,Int,Int)] = {
      val c = Math.sqrt(a*a+b*b-1)
      val cInt = Math.round(c).toInt
      if (a+b+cInt > max) acc
      else if (c-b<0.9) acc
      else if (a*a+b*b==cInt*cInt+1 &&a<b+cInt && b<a+cInt && cInt<a+b) go(b+1,acc+((a,b,cInt)))
      else go(b+1,acc)
    }
    go(initialB,Set.empty)
  }
  val result = for{
    a <- 2 to aMax
  } yield {
    val initialB = a
    find(a,initialB)
  }

//  println(find(1,0))

  println(result.reduce(_++_).size+(max-1)/2)

}
