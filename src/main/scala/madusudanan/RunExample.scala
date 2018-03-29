package madusudanan

object RunExample extends App {

  val value = 10
  //implicit param to be used in method
  implicit val param = 2

  //implicit function to convert double to int
  implicit def doubleToInt(d: Double): Int = d.toInt

  val x: Int = 20.3

  def multiply(implicit param1: Int, param2: Int) = param1 * param2

  println(multiply)
  println(x)

}
