import java.io.File

import rational.Rational
import chapter_10.Spiral

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

    val pup: Friendly = new Dog with ExclamatoryDog
    println(pup.greet())

    val check: ChecksumCalculator = new ChecksumCalculator
    check.add(119)
    println(check.checksum)
    val printTable = new MultiplyTable
    printTable.multyplyTable
    println()
    println(ChecksumCalculator.calcChecksum("love"))

    val x = new Rational(1, 2)
    val y = new Rational(3, 4)
    println(x + y)

    println(Spiral.spiral(15, 0))
  }

}
