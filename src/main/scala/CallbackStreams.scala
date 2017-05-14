/**
  * Created by joe on 10/04/17.
  */
object CallbackStreams {

  case class Output() {
    var s:Stream[Int] = Stream.empty
    def put(t:Int):Unit = {
      s = s.append(List(t))
    }
    def take:Stream[Int] = s
  }
  def takesOutput(o:Output): Unit ={
    (1 to 10) map o.put
  }


}
