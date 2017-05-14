import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by joe on 30/03/17.
  */
object Coffee extends App{

  import scala.concurrent.ExecutionContext.Implicits.global
  case class CoffeeBeans()
  case class GroundCoffee()

  def grindCoffee(c:CoffeeBeans)= Future{
    println("grinding coffee")
    Thread.sleep(1000)
    println("coffee ground")
    GroundCoffee()
  }

  case class ColdWater()
  case class HotWater()

  def boilWater(w:ColdWater) = Future {
    println("boiling water")
    Thread.sleep(1000)
    println("water boiled")
    HotWater()
  }

  case class Coffee()

  def makeCoffee(water: HotWater,coffee: GroundCoffee):Future[Coffee] = Future{
    println("pouring")
    Thread.sleep(500)
    println("coffee!")
    Coffee()
  }


  val hotWaterf = boilWater(ColdWater())
  val groundCoffeef = grindCoffee(CoffeeBeans())
  val coffee = for {
    water <- hotWaterf
    groundCoffee <- groundCoffeef
    coffee <- makeCoffee(water,groundCoffee)
  } yield coffee

  Await.ready(coffee, 5.seconds)

}
