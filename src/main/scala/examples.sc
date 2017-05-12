
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

sumOld(1, 4)
summCurrying(4)(3)

val z = summCurrying(3) _
val zz = z(5)

//Remove elements in odd position in list

val fullList = List[Int](8, 15, 22, 1, 10, 6, 2, 18, 18, 18, 1)
def f(arr: List[Int]): List[Int] = {
    arr.zipWithIndex.filter(_._2 % 2 == 1).map(_._1)
}

f(fullList)
//Another example
def another_f(list: List[Int]): List[Int] = {
    val z = list.zipWithIndex.collect {
        case (value, index) if index % 2 == 1 => value
    }
    z
}

another_f(fullList)