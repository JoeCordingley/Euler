import scala.util.Random

/**
  * Created by joe on 14/01/17.
  */
object Fleas extends App{


  case class Flea(position:(Int,Int))
  type Movement = PartialFunction[Flea,Flea]
  val left:Movement = {case Flea((x,y)) if x > 0 => Flea(x-1,y)}
  val right:Movement = {case Flea((x,y)) if x < 29 => Flea(x+1,y)}
  val up:Movement= {case Flea((x,y)) if y < 29 => Flea(x,y+1)}
  val down:Movement = {case Flea((x,y)) if y > 0 => Flea(x, y-1)}
  val movements: List[Movement] = List(left,right,up,down)


//  type Square = List[Flea]
//  type Row = List[Square]
//  type Grid = List[Row]

  val Fleas = {
    for {
      i <- 0 to 29
      j <- 0 to 29
    } yield Flea(i,j)
  }.toList

  def jump(fleas:List[Flea], times:Int) : List[Flea] = times match{
    case 0 => fleas
    case _ => jump( for{
      flea <- fleas
      validMoves = movements.filter(movement => movement.isDefinedAt(flea))
      movement = validMoves(Random.nextInt(validMoves.size))
    } yield movement(flea), times -1)
  }

  def fleaCounts: List[List[Int]] = {
    val fleas = jump(Fleas,50)
    val emptyGrid = List.fill(30)(List.fill(30)(0))
    fleas.foldLeft(emptyGrid){
      case (grid,Flea((x,y))) => grid.updated(x,grid(x).updated(y,grid(x)(y)+1))
    }
  }

//  def emptySquares: Int = {
//    val fleas
//  }

  val x = fleaCounts
  x.foreach(println)
}
