package com.joecordingley

/**
  * Created by joe on 05/08/16.
  */
object Euler116 extends App{

  def noOfWays(rowLength:Int,pieceSize:Int,noOfPieces:Int):Int = {
    if ( noOfPieces == 0) 1
    else {
      val totalSpace = rowLength - noOfPieces * pieceSize
      (0 to totalSpace)
        .map(x => noOfWays(rowLength - x - pieceSize, pieceSize, noOfPieces - 1))
        .sum
    }
  }

  def noOfWays(rowLength:Int,pieceSize:Int):Int = {
    val maxPieces = rowLength / pieceSize
    (1 to maxPieces).map(x=> noOfWays(rowLength,pieceSize,x)).sum
  }

  def noOfWays(rowLength: Int) :Int = (2 to 4).map(x=>noOfWays(rowLength,x)).sum

  println(noOfWays(50))

}
