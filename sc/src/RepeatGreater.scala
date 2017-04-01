/**
  * Created by Denis on 23-Mar-17.
  */
class RepeatGreater(greeting: String, count: Int) {
  def this(greeting: String) = this(greeting, 1)

  def greet() = {
    for(i <- 1 to count){
      println(greeting)
    }
  }

}
