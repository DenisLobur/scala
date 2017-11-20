package essential_slick

import dataRoot.db.Example.{MessageTable, db, exec, messages}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.util.Try

object Messanger extends App {
  val db = Database.forURL("jdbc:postgresql://localhost/message?user=postgres&password=123456789")

  // Base query for querying the messages table:
  lazy val messages = TableQuery[MessageTable]

  // Helper method for running a query in this example file:
  def exec[T](program: DBIO[T]): T = Await.result(db.run(program), Duration.Inf)

  // Drop table if exists, then recreate and populate
  exec(messages.schema.drop.asTry andThen messages.schema.create andThen (messages ++= freshTestData))

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

  exec(messages += addMessage)
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

  println("force insert of Primary key")
  //  val forceInsertAction = messages forceInsert Message("HAL", "I'm a computer, what would I do with a Christmas card anyway?",
  //    1000L)
  //  exec(forceInsertAction)

  println("Check if id = 1000L was inserted")
  println(exec(messages.filter(_.id === 1000L).exists.result))

  //  println("Insert message with getting back its id")
  //  val insertWithBackedId = messages returning messages.map(_.id) += Message("Dave", "Point taken")
  //  println(exec(insertWithBackedId))

  //  println("returning just inserted message")
  //  println(exec(messages returning messages += Message("Dave", "So... what do we do now?")))

  println("Delete 1000th id")
  val deleteThousand = messages.filter(_.id === 1000L).delete
  exec(deleteThousand)

  println("Remove all rows and restore them back")
  val removeAndRestore = messages.delete andThen (messages ++= freshTestData) andThen messages.result
  //exec(removeAndRestore)

  println("Update HAL's name")
  val updateQuery = messages.filter(_.sender === "HAL").map(_.sender).update("HAL 9000")
  //exec(updateQuery)

  println("Update multiple columns")
  val updateMultiple = messages.filter(_.id === 22L).map(message => (message.sender, message.content))
    .update("Dave 100", "This is not burito")
  exec(updateMultiple)

  println("Delete all messages")
  exec(messages.delete)

  println("Insert 'First' before first message")

  def insertFirst(m: Message): DBIO[Int] = {
    messages.size.result.flatMap {
      case 0 => (messages += Message(m.sender, "First!")) andThen (messages += m)
      case n => messages += m
    }
  }

  println("inserting one message to db")
  exec(insertFirst(Message("Me", "Hello")))

  println("checking the bd after insertion")
  exec(messages.result).foreach(println)

  exec(messages ++= freshTestData)

  def onlyOne[T](action: DBIO[Seq[T]]): DBIO[T] = action.flatMap {
    case m +: Nil => DBIO.successful(m)
    case y +: ys => DBIO.failed(new RuntimeException(s"Expected one result, ${(y +: ys).length}"))
  }

  val happy = messages.filter(_.content like "%sorry%").result
  val boom = messages.filter(_.content like "%I%").result

  println("check if success or fail")
  //println(exec(onlyOne(happy)))
  //exec(onlyOne(boom))

  def exactlyOne[T](action: DBIO[Seq[T]]): DBIO[Try[T]] = onlyOne(action).asTry
  println(exec(exactlyOne(happy)))
  println(exec(exactlyOne(boom)))

  def myFilter[T](action: DBIO[T])(p: T => Boolean)(alternative: => T) = action.map {
    case t if p(t) => t
    case _ => alternative
  }

  println(exec(myFilter(messages.size.result)(_ > 100)(100)))

  def freshTestData = Seq(
    Message("Dave", "Hello, HAL. Do you read me, HAL?"),
    Message("HAL", "Affirmative, Dave. I read you."),
    Message("Dave", "Open the pod bay doors, HAL."),
    Message("HAL", "I'm sorry, Dave. I'm afraid I can't do that.")
  )
}
