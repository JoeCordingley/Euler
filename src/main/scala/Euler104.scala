/**
  * Created by joe on 06/04/17.
  */
object Euler104 extends App {

  val oneToNine :Set[Char] = (1 to 9)
    .map(_.toString)
    .reduce(_++_)
    .toCharArray
    .toSet
  def isOneToNine(s:String):Boolean = {
    def go(s:String,set:Set[Char]):Boolean =
      if (s.isEmpty)
        true
      else
        oneToNine(s.head) && (! set(s.head)) && go(s.tail,set+s.head)

    go(s,Set.empty)
  }

  val fibs :Stream[Int] = 1#:: 1#:: fibs.zip(fibs.tail).map{case (a,b)=> a+b}
  val ans = fibs
    .map{_.toString()}
    .zipWithIndex
    .dropWhile{case (s,_)=> s.length < 9}
    .map{case (s,i) => (i,(s.take(9),s.takeRight(9)))}
    .find{case (_,(s1,s2))=> isOneToNine(s1) && isOneToNine(s2)}
  println(ans)


}
