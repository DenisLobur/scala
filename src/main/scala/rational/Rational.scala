package rational

/**
  * Created by Denis on 01-Apr-17.
  */
class Rational(num: Int, den: Int) {
  private val g = gcd(num, den)
  val numer: Int = num / g
  val denom: Int = den / g

  def this(n: Int) = this(n, 1)

  def add(that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def sub(that: Rational): Rational = {
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  }

  def mul(that: Rational): Rational = {
    new Rational(numer * that.numer, denom * that.denom)
  }

  def div(that: Rational): Rational = {
    new Rational(numer * that.denom, denom * that.numer)
  }

  override def toString() = "rational: " + numer + "/" + denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}
