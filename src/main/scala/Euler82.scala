import scala.io.Source

/**
  * Created by joe on 04/03/17.
  */
object Euler82 extends App{

  def doRow(l1: List[Int], l2: List[Int]) : List[Int] = {
    def go(left: Int, l1: List[Int], l2: List[Int]) : List[Int]= l2 match{
      case x :: Nil => x + (left min l1.head):: Nil
      case x :: _ =>
        val left2 = x + (left min l1.head)
        val rest = go(left2,l1.tail,l2.tail)
        x + (left min l1.head min rest.head) :: rest
    }
    go(Int.MaxValue,l1,l2)
  }

  val grid = Source.fromResource("p082_matrix.txt")
    .getLines()
    .map(s=>s.split(",").map(i => i.toInt).toList)
    .toList
    .transpose
    .toStream
  val runningTotal : Stream[List[Int]]  = grid.head #:: grid.tail.zip(runningTotal).map{
    case (l1,l2) => doRow(l2,l1)
  }
  val answer = runningTotal.last.min
  println(answer)



}
