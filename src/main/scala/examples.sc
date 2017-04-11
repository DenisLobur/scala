
// functional literal
val x = (z: Int) => z + 1
x(4)

val someNumbers = List(1, 2, 3, 4, 5)
someNumbers.foreach(x => println(x))
someNumbers.filter(p => p > 2)
someNumbers.foreach(println)

// placeholder
someNumbers.filter(_ > 2)
val summ = (_: Int) + (_: Int)
summ(1, 10)

def sum(a: Int, b: Int, c: Int) = a + b + c
sum(b = 3, a = 11, c = 8) // named params can be in any order
val az = sum _
az(2, 4, 6)

val b = sum(1, _: Int, 3)
b(2)

//Repeating params
def repeat(a: String*) = for (s <- a) println(s)
repeat("one")
repeat("one", "two", "three")

someNumbers.exists(_ > 0)

//Currying
def sumOld(a: Int, b: Int) = a + b
def summCurrying(a: Int)(b: Int) = a + b

sumOld(1,4)
summCurrying(4)(3)

val z = summCurrying(3)_
val zz = z(5)
