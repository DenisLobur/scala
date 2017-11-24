package essential_slick

import UserTable._
import UserMessageTable._
import RoomTable._
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
    _ <- (users.schema ++ userMessages.schema).drop.asTry andThen (users.schema ++ userMessages.schema).create
    daveId <- insertUsers += User("Dave")
    halId <- insertUsers += User("Hal")
    rowsAdded <- userMessages ++= freshTestData(daveId, halId)
  } yield rowsAdded

  exec(setup)

  //monadic join
  val query = for {
    msg <- userMessages
    usr <- msg.sender
  } yield (usr.name, msg.content)

  //monadic join using flatMap
  val queryFlatMap = userMessages flatMap {
    msg =>
      msg.sender.map {
        usr => (usr.name, msg.content)
      }
  }

  println("join using 'for'".toUpperCase)
  exec(query.result).foreach(println)

  println("join using 'flatMap'".toUpperCase)
  println(query.result.statements.mkString.toUpperCase)
  exec(queryFlatMap.result).foreach(println)

  val queryJoin: Query[(UserTable, UserMessageTable), (User, UserMessage), Seq] = {
    users join userMessages on {
      case (u, m) => u.id === m.senderId
    }
  }

  println("join using 'join... on'".toUpperCase)
  exec(queryJoin.result).foreach(println)

  //Delete messages table as we add 'Room' table
  exec(userMessages.schema.drop)

  val daveId = 1L
  val halId = 2L

  val setupWithRoom = for {
    _ <- (userMessages.schema ++ rooms.schema).drop.asTry andThen (userMessages.schema ++ rooms.schema).create
    airlockId <- insertRoom += Room("Air Lock")
    // Half the messages will be in the air lock room...
    _ <- insertUserMessage += UserMessage(daveId, "Hello, Hal! Do you read me?", Some(airlockId))
    _ <- insertUserMessage += UserMessage(halId, "Affirmative, Dave. I read you", Some(airlockId))

    // Half the messages will be in another room...
    _ <- insertUserMessage += UserMessage(daveId, "Open the pod bay doors, Hal")
    _ <- insertUserMessage += UserMessage(halId, "I'm sorry, Dave. I'm afraid I can not do that")

    msg <- userMessages.result
  } yield msg

  println("Query messages in Air Lock room and outside".toUpperCase)
  exec(setupWithRoom).foreach(println)
}
