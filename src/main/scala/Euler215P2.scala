///**
//  * Created by joe on 14/01/17.
//  */
//object Euler215P2 extends App{
//
//
//
//  case class validBrickAmount(twos:Int, threes:Int)
//
//  def validBrickAmounts(length:Int) : List[validBrickAmount] = {
//
//    val firstValidBrickAmount = length match {
//      case Even() => validBrickAmount(length /2,0)
//      case Odd() => validBrickAmount(length/2 -1,1)
//    }
//
//    val minThrees = firstValidBrickAmount.threes
//    val maxThrees = firstValidBrickAmount.threes + (firstValidBrickAmount.twos/3)*2
//
//    {
//      for {
//        threes <- minThrees to maxThrees by 2
//        twos = (length - 3*threes)/2
//      } yield validBrickAmount(twos,threes)
//    }.toList
//  }
//
//  object Even {
//    def unapply(arg: Int): Boolean = arg %2 == 0
//  }
//
//  object Odd {
//    def unapply(arg: Int): Boolean = arg %2 == 1
//  }
//
//
//  type Row = List[Int]
//  type Edge = List[Int]
//  type Wall = List[Row]
//
//  def getEdges(height:Int):List[Edge] = {
//    case 0 => List(Nil,Nil)
//    case Odd() => List(2 :: getEdges(height -1)(0),3:: getEdges(height-1)(1))
//    case Even() => List(3 :: getEdges(height -1)(0),2:: getEdges(height-1)(1))
//  }
//
//  println(getEdges(4))
//
////  def validWalls(length, height):List[Wall] = {
////    val edges = getEdges(height)
////    for {
////      leftEdge <- edges
////      rightEdge <- edges
////      wall <- getWa
////    }
////  }
//
//
//
//
//
//
//
//
//}
