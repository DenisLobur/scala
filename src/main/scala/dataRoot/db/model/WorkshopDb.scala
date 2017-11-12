package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object WorkshopDb extends App {
  println("hello world")
  val db = Database.forURL("jdbc:postgresql://127.0.0.1/postgres?user=postgres&password=123456789")
//  Await.result(db.run(CountryTable.table.schema.create), Duration.Inf)
//  Await.result(db.run(StaffTable.table.schema.create), Duration.Inf)
//  Await.result(db.run(GenreTable.table.schema.create), Duration.Inf)
//  Await.result(db.run(FilmTable.table.schema.create), Duration.Inf)
}