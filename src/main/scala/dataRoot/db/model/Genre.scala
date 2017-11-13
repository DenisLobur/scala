package dataRoot.db.model

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

case class Genre(id: Option[Long], title: String, description: Option[String])

class GenreTable(tag: Tag) extends Table[Genre](tag, "genres") {
  val id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  val title = column[String]("title")
  val description = column[Option[String]]("description")

  def * = (id.?, title, description) <> (Genre.apply _ tupled, Genre.unapply)
}

object GenreTable {
  val table = TableQuery[GenreTable]
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
