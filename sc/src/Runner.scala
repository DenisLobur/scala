import java.io.File

/**
  * Created by Denis on 21-Mar-17.
  */
object Runner {
  def main(args:Array[String]) {
    println("Hello world")
    val lineCalculator = new LineCalculator(new File("Runner.scala"))
  }

}
