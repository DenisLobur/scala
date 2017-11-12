package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class Staff(id: Option[Long], name: String, rate: String, age: String)

final class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("title")

  def rate = column[String]("String")

  def age = column[String]("age")

  def * = (id, name, rate, age).mapTo[Staff]
}

class StaffRepository(db: Database) {
  val staffTableQuery = TableQuery[StaffTable]

  def create(staff: Staff): Future[Staff] = {
    db.run(staffTableQuery returning staffTableQuery += staff)
  }

  def update(staff: Staff): Future[Int] = {
    db.run(staffTableQuery.filter(_.id === staff.id).update(staff))
  }

  def delete(staff: Staff): Future[Int] = {
    db.run(staffTableQuery.filter(_.id === staff.id).delete)
  }

  def getById(staffId: Long): Future[Option[Staff]] = {
    db.run(staffTableQuery.filter(_.id === staffId).result.headOption)
  }
}