package dataRoot.db.mapper

import dataRoot.db.model.Country

import dataRoot.db.model.Staff
import dataRoot.db.Example.Message
import dataRoot.db.model.{Country, Genre, Staff}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

final class CountryTable(tag: Tag) extends Table[Country](tag, "country") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def title = column[String]("title")

  def * = (id, title).mapTo[Country]
}
