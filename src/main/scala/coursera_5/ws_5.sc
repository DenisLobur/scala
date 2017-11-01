val pair = (42, "answer")

object mergeSort {
  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (xs, Nil) => xs
        case (Nil, ys) => ys
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n
      merge(msort(fst)(lt), msort(snd)(lt))
    }
  }

  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case x :: xs1 => x * factor :: scaleList(xs1, factor)
  }

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case x :: xs1 => (x * x) :: squareList(xs1)
  }

  def squareListViaMap(xs: List[Int]): List[Int] = {
    xs map (x => x * x)
  }

  def positiveElements(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case x :: xs1 => if (x > 0) x :: positiveElements(xs1) else positiveElements(xs1)
  }

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val z:(List[T], List[T]) = xs span (y => y == x)
      z._1 :: pack(z._2)
  }

  def encode[T](xs: List[T]): List[(T, Int)] = {
    pack(xs) map (ys => (ys.head, ys.length))
  }

}

val list = List(-1, 2, -3, 4, -5, 6)
val listDuplicated = List("a", "a", "a", "b", "c", "c")

mergeSort.msort(List(2, 4, 1, 7, 3, 9, 4))((x, y) => x > y)
mergeSort.msort(List("|", "||", "|||"))((x, y) => x.compareTo(y) > 0)

mergeSort.scaleList(List(1, 2, 3, 4, 5), 2)

val squares = mergeSort.squareList(List(1, 2, 3, 4, 5))
val squaresViaMap = mergeSort.squareListViaMap(List(1, 2, 3, 4, 5))
val onlyPositive = mergeSort.positiveElements(list)
val onlyNegative = list filter (x => x < 0)
val onlyNegativeViaMap = list map (x => x * (-1))
val partition = list partition (x => x > 0)
val takeWhile = list takeWhile (x => x < 0)
val dropWhile = list dropWhile (x => x < 0)
val span = list span (x => x < 0)

val pack = mergeSort.pack(listDuplicated)
val encode = mergeSort.encode(listDuplicated)
val pair2: (Int, Int) = (2,3)