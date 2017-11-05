import scala.language.postfixOps
// Collections, xs - sequence
// xs exists p - true if there is an element x of xs such that p(x) holds,
// false otherwise
// xs forall p - true if p(x) holds for all elements x of xs, false otherwise
// xs zip ys - sequence of pairs drown from xs and ys
// xs.unzip - splits a sequence of pairs xs into two sequences consisting of
// the first, respectively second halves of all pairs
// xs.flatMap f - applies collection-valued function f to all elements of xs
// and concatenates the result
// xs.sum - sum of all elements
// xs.product - product of all elements
// xs.min - minimum of all elements
// xs.max - maximum of all elements

val s = "Hello World!"

s exists (c => c.isUpper)

s forall (c => c.isUpper)

val pairs = List(1, 2, 3) zip s

pairs unzip

s flatMap (c => List('.', c))

(1 to 5) flatMap (x => (1 to 4) map (y => (x, y)))

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
  (xs zip ys) map (xy => xy._1 * xy._2) sum
}

def scalarProductFor(xs: Vector[Double], ys: Vector[Double]): Double = {
  (for ((x, y) <- xs zip ys) yield x * y) sum
}

scalarProduct(Vector(1, 2, 3), Vector(3, 2, 1))
scalarProductFor(Vector(1, 2, 3), Vector(3, 2, 1))

def isPrime(n: Int): Boolean = (2 until n) forall (x => n % x != 0)

isPrime(67)

//pairs, h-o functs and for
val n = 5
(1 until n)
  .flatMap(i => (1 until i) map (j => (i, j)))
  .filter(pair => isPrime(pair._1 + pair._2))

for {
  i <- 1 until n
  j <- 1 until i
  if (isPrime(i + j))
} yield (i, j)

val countries = Map("Washington" -> "US", "Bern" -> "Switzerland")

countries get "Washington"
countries get "Java"

def showCountry(city: String) = countries get city match {
  case Some(city) => city
  case None => "Nothing found"
}

showCountry("Washington")

// sorted and groupBy

val fruit = List("apple", "pair", "orange", "pineapple")

fruit sortWith (_.length < _.length)
fruit sorted

fruit groupBy (_.head)
