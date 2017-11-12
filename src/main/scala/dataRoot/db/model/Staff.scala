package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

case class Staff(id: Option[Long], name: String, rate: String, age: String)

final class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("title")

  def rate = column[String]("String")

  def age = column[String]("age")

  def * = (id, name, rate, age).mapTo[Staff]
}