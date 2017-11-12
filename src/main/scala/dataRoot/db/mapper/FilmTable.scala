package dataRoot.db.mapper

import dataRoot.db.Example.Message
import dataRoot.db.model.{Country, Film, Genre, Staff}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/*case class FilmTable(tag: Tag) extends Table[Film](tag, "film") {

  val id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  val title = column[String]("title")

  val duration = column[Duration]("duration")

  val directorId = column[Long]("director")

  val rating = column[Double]("rating")


//  def * = (id, title, duration, directorId, rating) <> (Film.apply _ tupled._)
}*/
