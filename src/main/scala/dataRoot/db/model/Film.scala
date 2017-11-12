package dataRoot.db.model

import scala.concurrent.duration.Duration
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

case class Film(id: Option[Long], title: String, duration: String, director: String, rating: String, countries: String)

case class FilmTable(tag: Tag) extends Table[Film](tag, "film") {

  val id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  val title = column[String]("title")

  val duration = column[Duration]("duration")

  val directorId = column[Long]("director")

  val rating = column[Double]("rating")

  def * = (id, title, duration, directorId, rating).mapTo[Film]
  //  def * = (id, title, duration, directorId, rating) <> (Film.apply _ tupled, Film.unapply)
}
