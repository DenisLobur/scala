/**
  * Created by denis on 12/8/15.
  */
case class User(val email: Email) {
  override def toString(): String = s"[Email: ${email.address}]"
}
