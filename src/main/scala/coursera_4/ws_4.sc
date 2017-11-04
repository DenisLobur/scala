val fruits: List[String] = List("apples", "app")
val fruits2: List[String] = List("oranges")
val fruits3: List[String] = List("banana")
val fruitsList = fruits :: (fruits2 :: (fruits3 :: Nil))
fruitsList flatMap (li => li)

// Insertion sort of list
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}

def insertSort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case y :: ys => insert(y, insertSort(ys))
}

insertSort(List(1,3,2,5,4))

