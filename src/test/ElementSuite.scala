import org.scalatest.Suite
import chapter_10.Element.elem
/**
  * Created by denys on 4/19/17.
  */
class ElementSuite extends Suite {

  def testUniformElement(): Unit ={
    val ele = elem('x', 2, 3)
    assert(ele.width == 2)
  }

}
