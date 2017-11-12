package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class Genre(id: Option[Long], title: String, description: String)

final class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
  def id = column[Long]("id", O.PrimaryKey)

  def title = column[String]("title")

  def description = column[String]("description")

  def * = (id.?, title, description) <> (Genre.apply _ tupled, Genre.unapply)
}

object GenreTable {
  lazy val table = TableQuery[GenreTable]
}

class GenreRepository(db: Database) {
  val genreTableQuery = TableQuery[GenreTable]

  def create(genre: Genre): Future[Genre] = {
    db.run(genreTableQuery returning genreTableQuery += genre)
  }

  def update(genre: Genre): Future[Int] = {
    db.run(genreTableQuery.filter(_.id === genre.id).update(genre))
  }

  def delete(staff: Genre): Future[Int] = {
    db.run(genreTableQuery.filter(_.id === staff.id).delete)
  }

  def getById(genreId: Long): Future[Option[Genre]] = {
    db.run(genreTableQuery.filter(_.id === genreId).result.headOption)
  }
}
