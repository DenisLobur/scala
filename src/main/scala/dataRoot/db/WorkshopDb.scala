package dataRoot.db

import dataRoot.db.model.{Country, Genre, Staff}
import dataRoot.db.mapper.CountryTable
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object WorkshopDb extends App {
  println("hello world")
  val db = Database.forURL("jdbc:postgresql://127.0.0.1/filmoteka?user=postgres&password=root")
  Await.result(db.run(CountryTable.table.schema.create), Duration.Inf)

}