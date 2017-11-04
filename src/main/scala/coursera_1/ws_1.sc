// if(b) e1 else e2
// and(x,y) = x&&y
// or(x,y) = x||y

def and(a: Boolean, b: Boolean): Boolean = {
  if (a) b else false
}

and(false, true)

// Square root Newton's law
def sqrt(x: Double) = {
  def abs(x: Double) = if (x < 0) -x else x

  def sqrtIter(guess: Double): Double = {
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))
  }

  def isGoodEnough(guess: Double) = {
    abs(guess * guess - x) / x < 0.001
  }

  def improve(guess: Double) = {
    (guess + x / guess) / 2
  }

  sqrtIter(1.0)
}

sqrt(2)
sqrt(4)
sqrt(1e-6)
sqrt(1e60)

// GCD
def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)

gcd(7, 21)

// factorial
def factorial(x: Int): Int = {
  if (x == 1) 1
  else x * factorial(x - 1)
}

factorial(5)

// factorial recursive
def factorialRecur(x: Int) = {
  def loop(acc: Int, x: Int): Int =
    if (x == 0) acc
    else loop(acc * x, x - 1)

  loop(1, x)
}

factorialRecur(5)



val list = List("orange", "apple", "banana")

for (x <- list) {
  println(x)
}

list.foreach(println(_))

//def sum(f: Int => Int):(Int, Int) => Int = {
//
//}