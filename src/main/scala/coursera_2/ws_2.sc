// Calculate sum of integers between a and b
def sumInts(a: Int, b: Int): Int = {
  if (a > b) 0
  else a + sumInts(a + 1, b)
}

sumInts(1, 4)

// Calculate sum of cubes between a and b
def cube(a: Int) = a * a * a

cube(2)

def sumCubes(a: Int, b: Int): Int = {
  if (a > b) 0
  else cube(a) + sumCubes(a + 1, b)
}

sumCubes(1, 3)

// Generic sum of f(x) between a and b
def sumGeneric(f: Int => Int, a: Int, b: Int): Int = {
  if (a > b) 0
  else f(a) + sumGeneric(f, a + 1, b)
}

sumGeneric(x => x * x * x, 1, 3)

// Recursive sum
def sumRecursive(f: Int => Int, a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int =
    if (a > b) acc
    else loop(a + 1, f(a) + acc)

  loop(a, 0)
}

sumRecursive(x => x * x * x, 1, 3)

//Curring
def sumCurring(f: Int => Int): (Int, Int) => Int = {
  def sumF(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sumF(a + 1, b)

  sumF
}

val sc = sumCurring(x => x * x * x)

// Product
def product(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
}

product(x => x * x)(3, 4)

//Factorial using product
def factorial(x: Int) = product(x => x)(1, x)

factorial(5)


