package chapter_9

import java.io.{File, PrintWriter}
import java.util.Date

/**
  * Created by denys on 4/11/17.
  */
object Currying {
  // Old way of presenting function
  def sumOldWay(a: Int, b: Int) = a + b

  //Currying way of presenting function
  def sumCurryingWay(a: Int)(b: Int) = a + b

  sumOldWay(1, 3)
  sumCurryingWay(2)(3)

  // fun(x,y) = fun(x)(y)

  //Substituting two parameter lists with one arg instead of one parameter list with two args
  def withPrintWriter(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  // ^ is equivalent to v

  // the first argument list, which contains one File argument, is
  // written surrounded by parentheses. The second argument list, which contains
  // one function argument, is surrounded by curly braces
  val file = new File("date.txt")
  withPrintWriter(file) {
    writer => writer.println(new java.util.Date)
  }
}
