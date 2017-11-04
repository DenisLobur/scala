// Collections, xs - list
// xs.length - number of elements in xs
// xs.last - list's lst element, exception if list is empty
// xs.init - a list consisting of all elements of the list except the last one,
// exception if xs is empty
// xs take n - a list consisting of the first n elements of xs or xs if it's length
// les then xs
// xs drop n - rest of the collection after taking n elements
// xs(n) - element of xs at index n
// xs ++ ys - concatination of two lists xs and ys
// xs.reverse - reverse of xs
// xs updated (n, x) - the list containing the same elements that xs, except
// at index n where it contains x
// xs indexOf x - index of first occurrence
// xs contains x - same as xs indexOf x>=0

val listOfInts = List(1, 2, 3, 4, 5)
def last[T](xs: List[T]): T = xs match {
  case List() => throw new NoSuchElementException
  case List(x) => x
  case y :: ys => last(ys)
}

last(listOfInts)

def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("emty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

init(listOfInts)

def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}

concat(listOfInts, listOfInts)


def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case y :: ys => reverse(ys) ++ List(y)
}

reverse(listOfInts)


def removeAt(n: Int, xs: List[Int]) = (xs take n) ::: (xs drop n + 1)

removeAt(2, listOfInts)

def squareListWithPattern(xs: List[Int]): List[Int] = xs match {
  case Nil => List()
  case y :: ys => (y * y) :: squareListWithPattern(ys)
}

squareListWithPattern(listOfInts)

def squareListWithMap(xs: List[Int]): List[Int] = xs map (x => x * x)

squareListWithMap(listOfInts)

//some high order functions
listOfInts filter (x => x > 3)
listOfInts filterNot (x => x > 3)
listOfInts partition (x => x > 3)

listOfInts takeWhile (x => x < 3)
listOfInts dropWhile (x => x < 3)
listOfInts span (x => x < 3)























