package dataRoot

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.duration._

package object db {
  implicit val durationToLongMapper = MappedColumnType.base[Duration, Long] (_.toSeconds, _.seconds)
}
