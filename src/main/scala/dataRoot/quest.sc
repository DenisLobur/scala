//#1

val code = "GHMABGZ VKXTMXL LNVVXLL EBDX GHG-LMHI, XGMANLBTLMBV XYYHKM"
val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

def check(): String = {
  // forloop
  val shift = 7

  //What is the code we want to encrypt/decrypt
  val inputText = code

  //Lets Encrypt/Decrypt the code
  val outputText = inputText.map((c: Char) => {

    //We find the c char in our allowed alphabet
    val x = alphabet.indexOf(c.toUpper)

    //If the c char is in our alphabet then we encrypt it
    //If it is not then we leave it be.
    if (x == -1) {
      c
    }
    else {
      alphabet((x + shift) % alphabet.size)
    }
  })

  //Print the result
  outputText
}

val solution = check().toLowerCase.split(' ').map(x => x.head).toList
println(s"#1 - ${solution}")
//#2

val one = "10001011101010101010000111110111011110101010101101110101010101010010000010110100101010101011011010100101011010101010101010101010101110101011000101101011110101010101010101010001010101010101101010101010101010101010101010111000001010101111010100111010101001011101010111111111101010101111111101010111110101001010101111110111101011010111111101011110101111111111111101111111111010101111101010101001111101010101010100100101010111101001010101001010101001010111110101010101010101011110101010010101001111101010100101111101010101001111111111101010111111111101001010111111110110101001111101010101111111010110100011111111111010101101011111110101010101110101010101010001110111101010101010101010101000001010110111111010101010010101011110101010000001010101000000000000101001111100000000000010010101010000001"
val two = "11100101000010101000001010010000010101011000110000110101000001010100000010000000010101100000110100100010111111111111111010010001010000001000000100000101011110101000000001010100000001010100101010111001010100000000000010101010101101010010101010101111001010000000000000001010010100111000010000000010100001010101000000110000001010101000000000000101001111100000000000010010101010000001"

def getBit(s: String, index: Int): Int = {
  if (index < 0 || index >= s.length) return 0
  if (s.charAt(index) == '0') 0
  else 1
}

def addBinary(a: String, b: String) = {
  val la = a.length
  val lb = b.length
  val max = Math.max(la, lb)
  val sum = new StringBuilder("")
  var carry = 0
  var i = 0
  while ( {
    i < max
  }) {
    val m = getBit(a, la - i - 1)
    val n = getBit(b, lb - i - 1)
    val add = m + n + carry
    sum.append(add % 2)
    carry = add / 2

    {
      i += 1;
      i - 1
    }
  }
  if (carry == 1) sum.append("1")
  sum.reverse.toString
}

val result = addBinary(one, two)

val zeroes = result.toList filter (x => x.equals('0'))

zeroes.size

val zerosAndOnes = result.toList partition (x => x.equals('1'))

val onesSize = zerosAndOnes._1.size

val zeroesSize = zerosAndOnes._2.size

val url = onesSize - zeroesSize

println(s"#2 - ${url}")

// #3

// Find sum of polyndromes

def toBinary(n: Int): String = n match {
  case 0 | 1 => s"$n" // List(n)
  case _ => s"${toBinary(n / 2)}${n % 2}" //
}

toBinary(120)

val poly = toBinary(120)
poly.toList

def isPalindrome[A](l: List[A]): Boolean = l match {
  case Nil => true
  case List(a) => true
  case list => list.head == list.last && isPalindrome(list.tail.init)
}

isPalindrome(List(1, 0, 0, 0, 0, 0, 0, 1))

val range = (1 to 1380).toList
val z = for {
  i <- range
  if isPalindrome(toBinary(i).toList)
} yield i

val poliSize = z.size
def sumFoldLeft(xs: List[Int]): Int = (0 :: xs foldLeft 0) (_ + _)

val ans3 = sumFoldLeft(z)

// answer = 34223

println(s"#3 - ${ans3}")

//#4

val initArr = "-1,-1,-2,-2,1,-5,1,0,1,14,-8,4,5,-11,13,5,7,-10,-4,3,-6,8,6,2,-9,-1,-4,0"

val listOfNums = initArr.split(",").map(_.toInt).toList

for {
  i <- 0 to listOfNums.size
  j <- 0 to listOfNums.size
  k <- 0 to listOfNums.size
  if (i + j + k) == 0
} yield (i, j, k)











