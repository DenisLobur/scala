/**
  * Created by denis on 12/8/15.
  */
object ScalaApp {
  def main(args: Array[String]) {
    val user: User = User("denisdenisdenis@bigmir.net")
    val nullable: Option[User] = Some(user)
    nullable.map((user: User) => {
      print("Found user: %s".format(user))
    })
  }
}
