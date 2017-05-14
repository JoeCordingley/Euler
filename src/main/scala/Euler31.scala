object Euler31 extends App{
  val Coins = List(200,100,50,20,10,5,2,1)
  val Objective = 200

  val countCombinations = {
    def go(lastCoin:Int, total:Int): Int = {
      if (total == Objective) 1
//      else coins.filter( coin => coin <= lastCoin && coin <= Objective - total).map(coin => go(coin, total + coin)).sum
      else {
        for {
          coin <- Coins
          if coin >= lastCoin
          if coin <= Objective - total
        } yield go(coin,total + coin)
      }.sum
    }
    go(0,0)
  }


  println(countCombinations)

}

