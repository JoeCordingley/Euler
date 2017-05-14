/**
  * Created by joe on 14/01/17.
  */
object Euler215 extends App{



  case class validBrickAmount(twos:Int, threes:Int)

  def validBrickAmounts(length:Int) : List[validBrickAmount] = {

    val firstValidBrickAmount = length match {
      case Even() => validBrickAmount(length /2,0)
      case Odd() => validBrickAmount(length/2 -1,1)
    }

    val minThrees = firstValidBrickAmount.threes
    val maxThrees = firstValidBrickAmount.threes + (firstValidBrickAmount.twos/3)*2

    {
      for {
        threes <- minThrees to maxThrees by 2
        twos = (length - 3*threes)/2
      } yield validBrickAmount(twos,threes)
    }.toList
  }

  object Even {
    def unapply(arg: Int): Boolean = arg %2 == 0
  }

  object Odd {
    def unapply(arg: Int): Boolean = arg %2 == 1
  }


  type Row = List[Int]
  type Wall = List[Row]

  def brickPermutations(length:Int): List[Row] = {
    for {
      validBrickAmount <- validBrickAmounts(length)
      permutation <- {
        List.fill(validBrickAmount.twos)(2) ::: List.fill(validBrickAmount.threes)(3)
      }.permutations
    } yield permutation
  }

  println(brickPermutations(9))

  def getRunningTotals(list : Row):List[Int] = {
    lazy val answer:Stream[Int] = (0 #:: answer).zip(list).map{case (x,y) =>x+y }
    answer.toList
  }

  def pairIsValid(pair:(Row,Row)):Boolean = {
    val length = pair._1.sum
    val runningtotals = (getRunningTotals(pair._1),getRunningTotals(pair._2))
    (2 to length -2).forall{ wallPosition =>
      !(runningtotals._1.contains(wallPosition) && runningtotals._2.contains(wallPosition))
    }
  }


  def validWalls(length:Int,height:Int): List[Wall] = {
    def go(heightSoFar:Int,acc:List[Wall]):List[Wall] = if (heightSoFar == height) acc else go(heightSoFar +1, for {
      wall @ row :: _ <- acc
      brickPerm <- brickPermutations(length)
      if pairIsValid((row,brickPerm))
    } yield brickPerm :: wall)
    go(1,brickPermutations(length).map(List(_)))
  }

  println(validWalls(32,10).length)







}
