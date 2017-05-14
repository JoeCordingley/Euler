
/**
  * Created by joe on 30/03/17.
  */
import scala.collection.mutable
object Euler14 extends App{

  def answerMap = mutable.Map.empty[Int,List[Int]]

  def sequence(n:Int):List[Int] = n match {
    case 1 => 1::Nil
    case _ if (n%2== 0 ) => n :: answerMap.getOrElseUpdate(n/2,sequence(n/2))
    case _ => n:: { x:Int => answerMap.getOrElseUpdate(x,sequence(x))}.apply (3*n+1)
  }
  def sequence2(n:Int) : List[Int] ={

    def go(acc:List[Int],n:Int) : List[Int] = n match{
      case 1 => 1 :: acc
      case _ if n%2 == 0 => if (answerMap contains(n/2)) answerMap(n/2) ::: n ::acc else go(n::acc,n/2)
      case _ => if (answerMap contains(n*3+1)) answerMap(n*3+1) ::: n ::acc else go(n::acc,n*3+1)
    }

    def processAnswers(l:List[Int]):Unit = if (l.isEmpty || answerMap.contains(l.head)) () else {
      answerMap.put(l.head,l)
      processAnswers(l.tail)
    }

    val answer = go(Nil,n)
    processAnswers(answer.reverse)
    answer

  }
  def sequence3(l:List[Int]):Map[Int,Int] = {
    def go(l:List[Int],acc:List[Int],finalAcc:Map[Int,Int]) : Map[Int,Int] =
      if (l.isEmpty) finalAcc
      else if (finalAcc.contains(acc.head)) {
        val firstValue = finalAcc(acc.head)
        val values = Stream.from(firstValue)
        val keys = acc
        val map = keys.zip(values).toMap
        if (l.tail.isEmpty) finalAcc ++ map
        else go(l.tail,List(l.tail.head),finalAcc++map)
      } else {
        val head = acc.head
        val next = if (head%2==0) head/2 else head*3+1
        go(l,next::acc,finalAcc)
      }
    go(l,List(l.head),Map(1->1))
  }
  val max = 1000*1000
//  val answer = sequence3((1 until max).toList).filterKeys(_<max).maxBy(_._2)._1
//  println(answer)
  def from(n: Long, c: Int = 0): Int = if (n == 1) c + 1 else
  from(if (n % 2 == 0) n / 2 else 3 * n + 1, c + 1)

  val r = (1 until 1000000).view.map(n => (n, from(n)))
    .reduceLeft((a, b) => if (a._2 > b._2) a else b)
  println(r)

}
