package chapter_10

/**
  * Created by denys on 4/12/17.
  */
class LineElement(s: String) extends Element {

  val contents = Array(s)

  override def width: Int = s.length

  override def height: Int = 1
}
