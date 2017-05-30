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
    dilimeter

    val pup: Friendly = new Dog with ExclamatoryDog
    println(pup.greet())
    dilimeter

    val check: ChecksumCalculator = new ChecksumCalculator
    check.add(119)
    println(check.checksum)
    val printTable = new MultiplyTable
    printTable.multyplyTable
    println()
    println(ChecksumCalculator.calcChecksum("love"))
    dilimeter

    val x = new Rational(1, 2)
    val y = new Rational(3, 4)
    println(x + y)
    dilimeter

    val oneHalf = new Rational(1, 2)
    val oneThird = new Rational(1, 3)
    println(oneHalf > oneThird)
  }

  private def dilimeter = println("\n=//=//=//=//=//=//=//=//=//=//=//=//=\n")

}
