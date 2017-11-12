package dataRoot.db.model

import scala.concurrent.duration.Duration
import slick.jdbc.PostgresProfile.api._

case class Film(id: Option[Long], title: String, duration: Duration, directorId: Long, rating: Double)

final class FilmTable(tag: Tag) extends Table[Film](tag, "film") {

  val id = column[Option[Long]]("id", O.PrimaryKey)

  val title = column[String]("title")

  val duration = column[Duration]("duration")

  val directorId = column[Long]("director")

  val rating = column[Double]("rating")

  def * = (id, title, duration, directorId, rating).mapTo[Film]
}

case class FilmToGenre(id: Option[Long], filmId: Long, genreId: Long)

class FilmToGenreTable(tag: Tag) extends Table[FilmToGenre](tag, "film_to_genre") {

  val id = column[Option[Long]]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val genreId = column[Long]("genre_id")



  def * = (id, filmId, genreId).mapTo[FilmToGenre]
}
