/**
  * Created by denis on 12/8/15.
  */
case class Email(val address: String) {
  val matcher = """([\w\.]+)@([\w\.]+)""".r.pattern.matcher(address)
  if(!matcher.matches()) {
    throw new IllegalArgumentException("Address is invalid")
  }
}

object Email {
  implicit def stringToEmail(address: String): Email = Email(address)
}

