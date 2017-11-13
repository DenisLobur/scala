package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

case class Staff(id: Option[Long], name: String, rate: Double, age: Int)

final class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  val id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  val name = column[String]("name")
  val rate = column[Double]("rate")
  val age = column[Int]("age")

  def * = (id.?, name, rate, age) <> (Staff.apply _ tupled, Staff.unapply)
}

object StaffTable {
  val table = TableQuery[StaffTable]
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