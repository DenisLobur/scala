package OOADSUS

import scala.io.StdIn

/**
  * Created by Denis on 07-Jun-17.
  */
object Libraries {
  def main(args: Array[String]): Unit = {
    println("What is your name?")
    val name = StdIn.readLine()
    println("How old are you?")
    val age = StdIn.readInt()

    val result = buildList()
    println(result)
    println(concatStrings(result))
    println(concatStringsPat(result))
  }

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
