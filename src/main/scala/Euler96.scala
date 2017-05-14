import scala.io.Source

/**
  * Created by joe on 08/03/17.
  */
object Euler96 {

//  type AvailableNums = Set[Int]
  val allNums = 1 to 9 toSet
//  type Column = List[AvailableNums]
//  type Row = List[AvailableNums]
//  type Square = List[AvailableNums]
//
  val testFile = Source.fromResource("suDokuTest")
//
  val testSudoku = testFile.getLines().map(l => l.map(c => c.asDigit).toList).toList

//  class Sudoku(val rows:List[Row]){
//
//  }

  type Grid = List[List[Int]]

  def noRepeats(l: List[Int]): Boolean = {
    def go(l:List[Int], s:Set[Int]) :Boolean = l match {
      case Nil => true
      case x :: xs => if (s(x)) false else go(xs, s+x )
    }
    go(l.filter(_!=0),Set.empty)
  }

  def transpose(grid:Grid): Grid = if (grid.head.isEmpty) Nil else grid.map(_.head) :: transpose(grid.map(_.tail))
  def transformAndValidate(grid:Grid,f:Grid=>Grid) = f(grid).forall(noRepeats)
  def getSquares(grid: Grid) : Grid = for {
    i <- (0 to 2).toList
    j <- 0 to 2
    miniGrid = grid.drop(3*i).take(3).flatMap(x => x.drop(3*i).take(3))
  } yield miniGrid



  val transforms = List[Grid=>Grid](getSquares,transpose,identity)

  def isValid(grid: Grid):Boolean = transforms.forall(
    transform => {
      println(transform)
      transformAndValidate(grid, transform)
    }
  )

  def solve(grid: Grid) : List[Grid] = grid match{
    case isComplete() => List(grid)
    case _ => for {
      i <- (0 to 8).toList
      j <- 0 to 8
      if grid(i)(j) == 0
      x <- allNums
      xAdded : Grid = grid.updated(i,grid(i).updated(j, x))
      if true /*isValid()*/
      correctGrids <- solve(xAdded)
    } yield correctGrids
  }
  object isComplete{
    def unapply(arg: List[List[Int]]): Boolean = arg.forall(_.forall(_ != 0 ))
  }

}
