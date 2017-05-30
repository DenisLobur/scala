package chapter_16

/**
  * Created by denys on 5/30/17.
  */
object ListExamples extends App {
  println("Lists!")
  val xs: List[String] = List()
  val nums = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
  println(nums.isEmpty)

  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))

  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

  println(isort(2 :: 4 :: 3 :: 5 :: Nil))
  val a :: b :: c = nums
  println(a :: b :: c)

  def isortPattern(xs: List[Int]) = xs match {
    case List() => List()
    case x :: xs1 => insertPattern(x, isort(xs1))
  }

  def insertPattern(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insertPattern(x, ys)
  }
}
