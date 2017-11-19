package essential_slick

import dataRoot.db.Example.{MessageTable, db, exec, messages}
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await

object Messanger extends App {
  val db = Database.forURL("jdbc:postgresql://localhost/message?user=postgres&password=123456789")

  // Base query for querying the messages table:
  lazy val messages = TableQuery[MessageTable]

  // Helper method for running a query in this example file:
  def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds)

  //  exec(messages.schema.create)
  //  exec(messages ++= freshTestData)

  val daveSays = messages.filter(_.sender === "Dave")
  exec(daveSays.result).foreach {
    println
  }

  val daveMessageIds = daveSays.map(_.id)

  exec(daveMessageIds.result).foreach {
    println
  }

  val halSays = for {
    message <- messages if message.sender === "HAL"
  } yield message

  exec(halSays.result).foreach {
    println
  }

  val addMessage = Message("Dave", "What if I say 'Pretty please'?")

  //  exec(messages += addMessage)
  println("dave only")
  exec(messages.filter(_.sender === "Dave").result).foreach(println)

  // passing parameter of type  f: MessageTable => T
  val check = messages.filter {
    messageTable: MessageTable => messageTable.sender === "Dave"
  }
  println("content only")
  val onlyContent = messages.map(_.content)
  exec(onlyContent.result).foreach(println)

  println("pod only")
  val pods = messages.map(_.content).filter {
    content: Rep[String] => content like "%pod%"
  }
  exec(pods.result).foreach(println)

  println("select id and content as a Tuple")
  val idAndContent = messages.map(t => (t.id, t.content))
  exec(idAndContent.result).foreach(println)

  println("select content only and map it to TextOnly model")
  val textContent = messages.map(t => (t.id, t.content) <> (TextOnly.apply _ tupled, TextOnly.unapply))
  println(textContent.result.statements.mkString)
  exec(textContent.result).foreach(println)

  println("check if 'bay' exists in content")
  val isBayExists = for {
    message <- messages
    if message.content like "%bay%"
  } yield message

  println(exec(isBayExists.exists.result))

  println("sender ---> content")
  val senderMApsToContent = messages.map(m => m.sender ++ " ---> " ++ m.content)
  exec(senderMApsToContent.result).foreach(println)

  println("sort by sender name")
  val sortedByName = messages.sortBy(_.sender)
  exec(sortedByName.result).foreach(println)

  println("sort by multiple columns (sender, content)")
  val sortBySenderAndContent = messages.sortBy(m => (m.sender, m.content.desc))
  exec(sortBySenderAndContent.result).foreach(println)

  println("count all meassages")
  val count = messages.length.result
  println(exec(count))

  println("select message with id = 1")
  val idOne = for {
    message <- messages if message.id === 1L
  } yield message
  println(exec(idOne.result))

  println("check if there any Hal's mesages")
  val isThereHalMessages = messages.filter(_.sender === "HAL")
  println(exec(isThereHalMessages.exists.result))

  println("select only content of messages")
  val onlyMessages = messages.map(_.content)
  exec(onlyMessages.result).foreach(println)

  println("Find first message that HAL sent")
  val firstHalMessage = messages.filter(_.sender === "HAL").map(_.content)
  println(exec(firstHalMessage.result.head))

  println("Find next five messages HAL sent")
  val halNextFiveMessages = messages.filter(_.sender === "HAL").map(_.content).drop(1).take(5)
  exec(halNextFiveMessages.result).foreach(println)

  println("HAL's tenth through to twentieth messages")
  val halTenToTwenty = messages.filter(_.sender === "HAL").map(_.content).drop(10).take(10)
  exec(halTenToTwenty.result).foreach(println)

  println("message starts with 'open'")
  val messageOpen = messages.filter(_.content startsWith "Open")
  exec(messageOpen.result).foreach(println)

  println("all messages with 'do' in lower case")
  val allDoes = messages.filter(_.content.toLowerCase like "%do%")
  exec(allDoes.result).foreach(println)

  println("Append '!' at the end")
  exec(messages.map(_.content ++ "!").result).foreach(println)




  def freshTestData = Seq(
    Message("Dave", "Hello, HAL. Do you read me, HAL?"),
    Message("HAL", "Affirmative, Dave. I read you."),
    Message("Dave", "Open the pod bay doors, HAL."),
    Message("HAL", "I'm sorry, Dave. I'm afraid I can't do that.")
  )
}
