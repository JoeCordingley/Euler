import TicTacToe._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by joe on 16/03/17.
  */
class TicTacToeTest extends FlatSpec with Matchers{

  val board1  = Board(
    List(
      List(X,O,X),
      List(O,X,X),
      List(O,X,O)
    ),O
  )
  val board1Rotated = Board(
    List(
      List(O,O,X),
      List(X,X,O),
      List(O,X,X)
    ),O
  )
  val otherBoard = Board(
    List(
      List(X,X,O),
      List(O,O,X),
      List(X,O,X)
    ),O
  )
  "rotateNinety" should "do what it says" in {
    val expected = board1Rotated
    val actual = ninetyDegreeRotation(board1)
    actual should equal(expected)
  }

  "isTranslationOf" should "return true for a translation" in {
    board1 isTranslationOf board1Rotated should be (true)
  }
  it should "return false for another board" in {
    board1 isTranslationOf otherBoard should be (false)
  }
  "reduceBoardStates" should "get rid of translations" in {
    val testSet = Set(board1,board1Rotated,otherBoard)
    val returnedSet = reduceBoardStates(testSet)
    returnedSet.size should be(2)
    val (board1Translations,others) = returnedSet.span(_ isTranslationOf board1)
    (board1Translations.size,others.size) should be (1,1)
  }

  "amount of games " should "be 255168" in {
    assert(amountOfGames === 255168)
  }
  "amtgms" should "be" in {
    assert(amountOfGamesWithouReflection === 26830)
  }
}
