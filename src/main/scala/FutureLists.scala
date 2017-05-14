

import scala.collection.immutable.Seq
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

/**
  * Created by joe on 07/04/17.
  */
object FutureLists extends App{
  import scala.concurrent.ExecutionContext.Implicits.global

  sealed trait Item {
    def id: Int
  }
  case class NonTerminal(id:Int,l:List[Future[Item]]) extends Item
  case class Terminal(id:Int) extends Item

  def flatten[A](l:List[Future[A]]):Future[List[A]] = l match {
    case Nil => Future.successful(Nil)
    case f :: fs => for {
      a <- f.map(a=>List(a)).fallbackTo(Future.successful(Nil))
      as <- flatten(fs)
    } yield a ::: as
  }

  def flatten2[A](l:List[Future[A]]):Future[List[Either[Throwable,A]]] = l match {
    case Nil => Future.successful(Nil)
    case f :: fs => for {
      a <- f.map(a => Right(a)).recover{case e:Throwable => Left(e)}
      as <- flatten2(fs)
    } yield a :: as
  }


  def traipseC(i:Item):Future[List[Item]] = i match {
    case i@ Terminal(_) => Future(List(i))
    case i @ NonTerminal(_,l) => {
      val x: List[Future[List[Item]]] = l.map{
        (f: Future[Item]) => f.flatMap{
          traipseC
        }
      }
      val y: Future[List[List[Item]]] = Future.sequence(x)
      val z: Future[List[Item]] = y map (l => l.flatten)
      z map (l => i :: l)
    }
  }
  def t(i:Item):Future[List[Int]] = i match {
    case Terminal(id) => Future(List(id))
    case NonTerminal(id,l) => y(id,l)
  }
  def y(i:Int,l:List[Future[Item]]): Future[List[Int]] = {
    val x: List[Future[List[Int]]] = l map {_ flatMap t}
    Future.sequence(x).map(ll => ll flatMap(l => i :: l))
  }
  def futureToEither[A](f:Future[A]):Future[Either[Throwable,A]] = f.transform{
    case Success(a) => Success(Right(a))
    case Failure(e) => Success(Left(e))
  }
//  def f2e2[M[_],A](f:Future[M[A]]):M[Future[Either[A]]
  def fl[A](l:List[Future[List[Either[Throwable,A]]]]):Future[List[Either[Throwable,A]]] = l match {
    case Nil => Future.successful(Nil)
    case f :: fs => for {
      a: List[Either[Throwable, Either[Throwable, A]]] <- f.map(l => l.map(Right(_))).recover{case e:Throwable => List(Left(e))}
      b:List[Either[Throwable,A]] =  a.map(flatEither)
      as: List[Either[Throwable, A]] <- fl(fs)
    } yield b:::as
  }
//  def fl2[A](l:List[Future[List[Either[Throwable,A]]]]):Future[List[Either[Throwable,A]]] = l match {
//    case Nil => Future.successful(Nil)
//    case l => {
//      val x: List[Future[Either[Throwable, List[Either[Throwable, Any]]]]] = l map futureToEither
//    }
//  }
  def flatEither[A,B](either:Either[A,Either[A,B]]):Either[A,B] = either match {
    case Left(e)=> Left(e)
    case Right(Left(e))=> Left(e)
    case Right(Right(e))=> Right(e)
  }
  def b(f:Future[Item]): Future[List[Int]] = f flatMap {
    case Terminal(id) => Future(List(id))
    case NonTerminal(id,l) => {
      val messy: List[Future[List[Int]]] = l map b
      val nearlyThere: Future[List[List[Int]]] = flatten(messy)
      nearlyThere map (listOfLists => id :: listOfLists.flatten)
    }
  }
  def b2(f:Future[Item]): Future[List[Either[Throwable,Int]]] = f flatMap {
    case Terminal(id) => Future(List(Right(id)))
    case NonTerminal(id,l) => {
      val messy: List[Future[List[Either[Throwable, Int]]]] = l map b2
      val nearlyThere: Future[List[Either[Throwable, Int]]] = fl(messy)
      nearlyThere map ((listOfLists: List[Either[Throwable, Int]]) => Right(id) :: listOfLists)
    }
  }
  def a(i: Item):Future[List[Int]] = i match {
    case Terminal(id) => Future(List(id))
    case NonTerminal(id,l) => {
      val messy: List[Future[List[Int]]] = l map (_ flatMap a)
      Future.sequence(messy) map (l =>id :: l.flatten)
    }
  }

  def another(f:Future[Item]) = {
    val x = f map {
      case Terminal(id) => List(id)
      case NonTerminal(id, l) => {}

    }
  }
//  def traipse1(i:Item):Future[List[Item]] = i match {
//    case i@ Terminal(_) => Future(List(i))
//    case i @ NonTerminal(_,l) => Future.traverse(l map {_ flatMap(traipseC))})(a => a.flatten)
//  }
   def traipse(f:Future[Item]):Future[List[Int]] = f flatMap(traipseC) map {_.map{_.id}}
  val f1 = Future(Terminal(1))
  val f2 = Future(Terminal(2))
  val f3 = Future(NonTerminal(3,List(f1,f2)))
  val f4 = Future(NonTerminal(4,List(f3)))
  val answer = b2(f4)
  answer.onComplete{
    case Success(l) => println(l)
  }
  Await.ready(answer,4 seconds)

}
