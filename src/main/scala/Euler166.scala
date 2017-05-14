/**
  * Created by joe on 23/03/17.
  */
object Euler166 {

//  def getAmountOfPerms(s:Set[List[Int]]):Int = {
//    def isValidSoFar(ll:List[List[Int]]):Boolean = {
//      def getVerticals(ll:List[List[Int]]):List[List[Int]] = ll match {
//        case _ if ll.head.isEmpty => Nil
//        case _ =>ll.map (_.head) :: getVerticals (ll.map (_.tail) )
//      }
//      def getLeadingDiag(ll:List[List[Int]]):List[Int] =ll match {
//        case Nil => Nil
//        case _ if ll.head.isEmpty => Nil
//        case _ => ll.head.head :: getLeadingDiag(ll.tail.map(_.tail))
//      }
//      def getOtherDiag(ll:List[List[Int]]):List[Int] = ll match {
//        case Nil => Nil
//        case _ if ll.head.isEmpty => Nil
//        case _ => ll.head.last :: getOtherDiag(ll.tail.map(_.init))
//      }
//      val all = getLeadingDiag(ll)::getOtherDiag(ll)::getVerticals(ll)
//      all.forall{
//        l1 => s.exists{
//          l2 => l1.zip(l2).forall{
//            case (a,b) => a==b
//          }
//        }
//      }
//
//    }
//    def go(ll:List[List[Int]]):Int = for {
//      l <- s
//      if isValidSoFar(l:ll)
//    }
//  }
//  val permutationsOfFour = {
//    for {
//      i <- 0 to 9
//      j <- 0 to i
//      k <- 0 to j
//      l <- 0 to k
//    } yield List(i,j,k,l)
//  }.toSet
//  val m = permutationsOfFour.groupBy{
//    case List(i,j,k,l) => i+j+k+l
//  }
//  for {
//    total <- m.keys
//
//  }

}
