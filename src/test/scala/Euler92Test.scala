import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

/**
  * Created by joe on 08/03/17.
  */
class Euler92Test extends FlatSpec with Matchers{

  val grid = Source.fromResource("suDokuTest").getLines().map(l => l.map(c => c.asDigit).toList).toList

  "getSquares" should "return squares" in {
    val expectedHead = List(0,0,3,9,0,0,0,0,1)
    val actualHead = Euler96.getSquares(grid).head
    assert(actualHead == expectedHead)
  }

  "getColumns" should "return columns" in {
    val expectedHead = List(0,9,0,0,7,0,0,8,0)
    val actualHead = Euler96.transpose(grid).head
  }

  "valid" should "work" in {
    Euler96.isValid(grid)
  }
}
