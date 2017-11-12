package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object WorkshopDb extends App {
  println("hello world")
  val db = Database.forURL("jdbc:postgresql://127.0.0.1/postgres?user=postgres&password=123456789")
//  Await.result(db.run(CountryQuery.table.schema.create), Duration.Inf)
  Await.result(db.run(StaffQuery.table.schema.create), Duration.Inf)


}