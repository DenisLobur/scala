package chapter_16

import chapter_15.Number

/**
  * Created by denys on 5/30/17.
  */
object ListExamples extends App {
  println("Lists!")
  val xs: List[String] = List()
  val nums = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
  println(nums.isEmpty)
  println("Sum of odds: " + sumList(nums))
  //println("Length of list: " + getListSize(nums))

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

  def append[T](xs: List[T], ys: List[T]): List[T] =
    xs match {
      case List() => ys
      case x :: xs1 => x :: append(xs1, ys)
    }

  println(append[Int](List(1, 2), List(3, 4)))
  println(nums.head) // get first element in constant time
  println(nums.tail) // get all elements but first in constant time
  println(nums.init) // get all elements but last in linear time
  println(nums.last) // get last element in linear time

  def rev[T](xs: List[T]): List[T] =
    xs match {
      case List() => xs
      case x :: xs1 => rev(xs1) ::: List(x)
    }

  println(rev(nums).indices)

  val listOfWords = List("word", "another word", "again word")
  println(listOfWords)
  println(listOfWords.flatMap(_.toCharArray)) // listofWords.map(_.toCharArray).flatten
  println(listOfWords.zipWithIndex)
  val listOfNumbers = List(22, 33, 44)
  println(listOfWords.zip(listOfNumbers))
  println(listOfWords.zip(listOfNumbers).unzip)
  println(listOfNumbers.mkString("{", " ", "}"))
  listOfNumbers.iterator

  def sumList(xs: List[Int]): Int = {
    xs.filter(_ % 2 != 0).sum
  }

  def getListSize(xs: List[Int]): Int = {

    var ii = for {i <- xs} yield i

    ii.size
  }

}
