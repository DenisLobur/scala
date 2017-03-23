import java.io.File

/**
  * Created by Denis on 21-Mar-17.
  */
object Runner {
  def main(args: Array[String]) {
    println("Hello world")
    // val lineCalculator = new LineCalculator(new File("Runner.scala"))
    val one = new RepeatGreater("Hi there")
    val two = new RepeatGreater("hello", 2)
    one.greet()
    two.greet()
    println()

    val pup:Friendly = new Dog with ExclamatoryDog
    println(pup.greet())
  }

}
