/**
  * Created by joe on 16/03/17.
  */
object TicTacToe {

  case class Board(spaces:List[List[Space]], whosGo:Mark){
    def isTranslationOf(b:Board):Boolean = translations.exists(_(b)==this)
    private def lineComplete(l:List[Space]) = l.forall(_==X ) || l.forall(_==O)
    private def wonVertically = spaces.exists(lineComplete)
    private def wonHorizontally = {
      def go(ll:List[List[Space]]):Boolean = if (ll.head.isEmpty) false else lineComplete(ll.map(_.head)) || go(ll.map(_.tail))
      go(spaces)
    }
    private def wonDiagonally = {
      def leadingDiagonal(ll:List[List[Space]])  : List[Space] =
        if (ll.isEmpty || ll.head.isEmpty) Nil
        else ll.head.head :: leadingDiagonal(ll.tail.map(_.tail))
      def otherDiagonal (ll:List[List[Space]]) = leadingDiagonal(ll.reverse)
      lineComplete(leadingDiagonal(spaces)) || lineComplete(otherDiagonal(spaces))
    }
    def isWon = wonHorizontally || wonVertically || wonDiagonally
    def hasEnded = isWon || spaces.forall(_.forall(_.isInstanceOf[Mark]))
    def getBoards: List[Board] = for {
      rowIndex <- spaces.indices.toList
      row = spaces(rowIndex)
      spaceIndex <- row.indices
      space = row(spaceIndex)
      if space == EmptySpace
      newRow = row.updated(spaceIndex,whosGo)
      newBoard = spaces.updated(rowIndex,newRow)
      newGo = if (whosGo == X) O else X
    } yield Board(newBoard,newGo)
    def getBoardsWihoutReflections:List[Board] = reduceBoardStates(getBoards.toSet).toList
  }
  object Board{
    def empty:Board = Board(List.fill(3)(List.fill(3)(EmptySpace)),X)
  }
  sealed trait Space
  sealed trait Mark extends Space
  case object X extends Mark
  case object O extends Mark
  case object EmptySpace extends Space
//  case object EmptyBoard = Board(List.fill(3)(List.fill(3)(EmptySpace)))
  type Translation =  (Board => Board)
  val reflection = (b:Board) => Board(b.spaces.reverse,b.whosGo)
  def rotate90(ll:List[List[Space]]):List[List[Space]] = if (ll.head.isEmpty) Nil else ll.map(_.head).reverse :: rotate90(ll.map(_.tail))
  val ninetyDegreeRotation = (b:Board) => Board(rotate90(b.spaces),b.whosGo)
  val translations:Set[Translation] = {
    for {
      rotations <- 0 to 3
      reflections <- 0 to 1
      composed = (
        identity[Board] _
        :: List.fill(reflections)(reflection)
        ::: List.fill(rotations)(ninetyDegreeRotation)
        ).reduce(_ compose _)
    } yield composed
  }.toSet

  def composeTranslations(i:Int, t:Translation) :Translation = i match {
    case 0 => identity
    case _ => t compose composeTranslations(i-1,t)
  }

  def reduceBoardStates(s:Set[Board]) = {
    def go(acc:Set[Board],s:Set[Board]):Set[Board] = if ( s.isEmpty) acc
      else {
        val newBoard = s.head
        val newAcc = if (acc.exists(_ isTranslationOf newBoard)) acc else acc + newBoard
        go(newAcc,s.tail)
      }
    go(Set.empty,s)
  }

  def amountOfGames= {
    def games(board: Board):List[List[Board]] =
      if (board.hasEnded) List(Nil)
      else {
        for {
          newBoard <- board.getBoards
          game <- games(newBoard)
        } yield newBoard :: game
      }
    games(Board.empty).size
  }
  def amountOfGamesWithouReflection = {
    def games(board: Board):List[List[Board]] =
      if (board.hasEnded) List(Nil)
      else {
        for {
          newBoard <- board.getBoardsWihoutReflections
          game <- games(newBoard)
        } yield newBoard :: game
      }
    games(Board.empty).size
  }



}
