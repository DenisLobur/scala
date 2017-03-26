/**
  * Created by Denis on 26-Mar-17.
  */
class MultiplyTable {
  def multyplyTable: Unit = {
    for (i <- 1 to 10) {
      for (j <- 1 to 10) {
        val prod = (i * j).toString
        print(String.format("%4s", Array(prod)))
      }
      println()
    }
  }
}
