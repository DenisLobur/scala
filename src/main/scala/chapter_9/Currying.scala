package chapter_9

/**
  * Created by denys on 4/11/17.
  */
object Currying {
  // Old way of presenting function
  def sumOldWay(a: Int, b: Int) = a + b

  //Currying way of presenting function
  def sumCurryingWay(a: Int)(b: Int) = a + b

  sumOldWay(1,3)
  sumCurryingWay(2)(3)

  // fun(x,y) = fun(x)(y)
}
