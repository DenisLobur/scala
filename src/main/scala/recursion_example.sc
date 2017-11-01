def mod(n: Int)(x: Int) = ((x%n) == 0)
lazy val q = mod(3) _
val x = q(5)

def filter(xs: List[Int], p: Int => Boolean): List[Int] =
  if (xs.isEmpty) xs
  else if (p(xs.head)) xs.head :: filter(xs.tail, p)
  else filter(xs.tail, p)

val nums = List(1,2,3,4,5,6)
print(filter(nums, mod(2)))

def recList(list: List[Int]): Unit = {
  if(list isEmpty) return
  println(s"${list.head}" + "\n")
  recList(list.tail)
}

recList(nums)