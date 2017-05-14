package com.joecordingley

/**
  * Created by joe on 04/08/16.
  */
object Euler68 extends App{

  def pairUp(l:List[Int]):Stream[List[Int]] = l match{
    case Nil => Stream.Empty
    case x :: y :: xs => List(x,y) #:: pairUp(xs)
  }

  def ngonify(sides:Stream[List[Int]]):Stream[List[Int]] = {

    val head = sides.head

    def moveAcross(l1:List[Int], l2:List[Int]):List[Int] = l1 ::: l2(1) :: Nil

    def inner(s:Stream[List[Int]]):Stream[List[Int]] = s match {
      case x #:: Stream.Empty => moveAcross(x,head) #:: Stream.Empty
      case x1 #:: x2 #:: xs => moveAcross(x1,x2) #:: inner(x2#::xs)
    }

    inner(sides)
  }

  def isValid(sides:Stream[List[Int]]):Boolean = {
    val total = sides.head.sum
    sides.tail.forall(l =>l.sum == total)
  }

  def startsWithLowest(sides:Stream[List[Int]]):Boolean = {
    val first = sides.head.head
    sides.tail.forall(l => l.head > first)
  }

  val answer= (1 to 10)
    .toList
    .permutations
    .map(pairUp)
    .filter(startsWithLowest)
    .map(ngonify)
    .filter(isValid)
    .map( _.map( _.mkString("") ).mkString("") )
    .filter( _.length == 16 )
    .max

/*  val answerAgain: Iterator[List[Int]] = for {
    perm <- (1 to 10).toList.permutations
    pair <- pairUp(perm)
    
  }*/

  println(answer)

}
