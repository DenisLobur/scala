package dataRoot.db

import dataRoot.db.Example.Message
import dataRoot.db.model.{Country, Genre, Staff}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object WorkshopDb extends App {
  println("hello world")

  final class CountryTable(tag: Tag) extends Table[Country](tag, "country") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

    def title = column[String]("title")

    def * = (id, title).mapTo[Country]
  }

  final class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("title")

    def rate = column[String]("String")

    def age = column[String]("age")

    def * = (id, name, rate, age).mapTo[Staff]
  }

  final class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

    def title = column[String]("title")

    def description = column[String]("description")

    def * = (id, title, description).mapTo[Genre]
  }
}