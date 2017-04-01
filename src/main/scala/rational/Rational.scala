package rational

/**
  * Created by Denis on 01-Apr-17.
  */
class Rational(num: Int, den: Int) {

  def this(n: Int) = this(n, 1)

  private val g = gcd(num, den)
  val numer: Int = num / g
  val denom: Int = den / g

  def +(that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def +(that: Int): Rational = {
    this + new Rational(that)
  }

  def -(that: Rational): Rational = {
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  }

  def -(that: Int): Rational = {
    this - new Rational(that)
  }

  def *(that: Rational): Rational = {
    new Rational(numer * that.numer, denom * that.denom)
  }

  def *(that: Int): Rational = {
    this * new Rational(that)
  }

  def /(that: Rational): Rational = {
    new Rational(numer * that.denom, denom * that.numer)
  }

  def /(that: Int): Rational = {
    this / new Rational(that)
  }

  override def toString: String = "rational: " + numer + "/" + denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def lessThan(that: Rational): Boolean = {
    this.numer * that.denom < that.numer * this.denom
  }

  def max(that: Rational): Rational = {
    if (this.lessThan(that)) that else this
  }

  implicit def intToRational(x: Int): Rational = new Rational(x)
}
