package dataRoot.db.mapper

import dataRoot.db.Example.Message
import dataRoot.db.model.{Country, Genre, Staff}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

final class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def title = column[String]("title")

  def description = column[String]("description")

  def * = (id, title, description).mapTo[Genre]
}
