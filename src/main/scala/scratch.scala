package com.joecordingley

/**
  * Created by joe on 23/10/16.
  */
object scratch extends App{

  def factorial(n:Int):Int =  n match {
    case 0 => 1
    case _ => n * factorial(n-1)
  }

  (1 to 5).map(x=> println(factorial(x)))

}
