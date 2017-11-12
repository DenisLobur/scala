package dataRoot.db.mapper

import dataRoot.db.model.Staff
import dataRoot.db.Example.Message
import dataRoot.db.model.{Country, Genre, Staff}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

final class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("title")

  def rate = column[String]("String")

  def age = column[String]("age")

  def * = (id, name, rate, age).mapTo[Staff]
}
