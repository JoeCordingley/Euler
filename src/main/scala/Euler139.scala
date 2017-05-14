package com.joecordingley

import scala.collection.immutable.IndexedSeq

/**
  * Created by joe on 05/08/16.
  */
class Euler139 {

  val rights: List[(Int, Int, Int)] = {
    val p = 100 *1000 *1000
    for {
      i <- (1 to getJ(1, p)).toList
      j <- (1 to getJ(i, p)).toList
      if isSquare(i*i+j+j)
    } yield(i,j, math.sqrt(i*i+j*j).toInt)


  }
  def isSquare(n:Int):Boolean = {
    val sqrt = math.sqrt(n).toInt
    n == sqrt*sqrt
  }

  def getJ(i:Int,p:Int):Int = (p*p - 2 * p*i)/(2*p - 2*i)

}
