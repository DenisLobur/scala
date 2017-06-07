package OOADSUS

import java.io.PrintWriter

import scala.io.{Source, StdIn}

/**
  * Created by Denis on 07-Jun-17.
  */
object Libraries {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("src/main/scala/OOADSUS/matrix.txt")
    val lines = source.getLines()
    val matrix = lines.map(_.split(" ").map(_.toDouble)).toArray
    source.close()

    val pw = new PrintWriter("src/main/scala/OOADSUS/sumRows.txt")
    matrix.foreach(row => pw.println(row.sum))
    pw.close()

    println("What is your name?")
    val name = StdIn.readLine()
    println("How old are you?")
    val age = StdIn.readInt()

    val result = buildList()
    println(result)
    println(concatStrings(result))
    println(concatStringsPat(result))

    val plus3 = add(3)_
    val plus5 = plus3(5)
  }

  def add(x: Int) (y: Int) = x+y

  def buildList(): List[String] = {
    val input = StdIn.readLine()
    if (input == "quit") Nil
    else input :: buildList()
  }

  def concatStrings(words: List[String]): String = {
    if(words.isEmpty) ""
    else words.head + concatStrings(words.tail)
  }

  def concatStringsPat(words: List[String]): String = words match {
    case Nil => ""
    case h::t => h + concatStringsPat(t)
  }
}
