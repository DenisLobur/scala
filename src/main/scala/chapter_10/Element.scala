package chapter_10

/**
  * Created by denys on 4/12/17.
  */
abstract class Element {

  def contents: Array[String]

  def height: Int = contents.length

  def width: Int = if (height == 0) 0 else contents(0).length
}
