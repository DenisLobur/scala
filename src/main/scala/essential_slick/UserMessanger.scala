package essential_slick

import UserTable._
import UserMessageTable._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import slick.dbio.DBIO

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object UserMessanger extends App {

  val db = Database.forURL("jdbc:postgresql://localhost/user_message?user=postgres&password=123456789")

  def exec[T](program: DBIO[T]): T = Await.result(db.run(program), Duration.Inf)

  def freshTestData(daveId: Long, halId: Long) = Seq(
    UserMessage(daveId, "Hello, Hal! Do you read me?"),
    UserMessage(halId, "Affirmative, Dave. I read you"),
    UserMessage(daveId, "Open the pod bay doors, Hal"),
    UserMessage(halId, "I'm sorry, Dave. I'm afraid I can not do that")
  )

  val setup = for {
    _ <- (users.schema ++ userMessages.schema).create
    daveId <- insertUsers += User("Dave")
    halId <- insertUsers += User("Hal")
    rowsAdded <- userMessages ++= freshTestData(daveId, halId)
  } yield rowsAdded

  exec(setup)

}
