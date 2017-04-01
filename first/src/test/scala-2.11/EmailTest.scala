import org.scalatest.FunSuite

/**
  * Created by denis on 12/8/15.
  */
class EmailTest extends FunSuite {
  test("Email with valid address") {
    val email = Email("denisdenisdenis@bigmir.net")
    assert(email.address != null)
  }

  test("Email with invalid address") {
    intercept[IllegalArgumentException] {
      Email("denisdenisdenis@bigmir.net")
    }
  }
}
