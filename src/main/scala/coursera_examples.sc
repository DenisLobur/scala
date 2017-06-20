object ex {
  /*def sum(f: Int => Int, a: Int, b: Int) = {
    def loop(a: Int, acc: Int): Int =
      if (a > b) acc
      else loop(a + 1, f(a) + acc)

    loop(a, 0)
  }*/

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  def sumRecursive(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else a + sumRecursive(f, a + 1, b)
  }

  def productRecursive(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * productRecursive(f)(a + 1, b)
  }

  def productMapReduce(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)

  def fact(a: Int): Int = {
    //productRecursive(x => x)(1, a)
    productMapReduce(x => x)(1, a)
  }

  def sumMapReduce(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0)(a, b)

  sumRecursive(x => x, 2, 5)
  productRecursive(x => x * x)(2, 3)
  sumMapReduce(x => x)(2, 5)
  productMapReduce(x => x * x)(2, 3)

  fact(5)


}