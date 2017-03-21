import java.io.File

import scala.io.Source

/**
  * Created by Denis on 21-Mar-17.
  */
class LineCalculator(args: File) {


  def widthOfLength(s: String) = s.length.toString.length

  if (args.getName.length > 0) {
    val lines = Source.fromFile(args).getLines.toList

    val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)

    val maxWidth = widthOfLength(longestLine)

    for (line <- lines) {
      val numSpaces = maxWidth - widthOfLength(line)

      val padding = " " * numSpaces

      println(padding + line.length + " | " + line)
    }
  } else
    Console.err.println("Please enter filename")

}
