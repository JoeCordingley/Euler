/**
  * Created by joe on 30/03/17.
  */
object ForComprehension extends App{

  val  x = for {
    i <- 0 to 1
    j <- 0 to 1
  } yield (i,j)
  val y = (0 to 1).flatMap(i => (0 to 1).map(j => (i,j)))
  assert(x==y)
  println(x)

}
