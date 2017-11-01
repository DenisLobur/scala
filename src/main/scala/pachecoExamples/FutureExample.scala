package pachecoExamples

import scala.concurrent.Future
import scala.util.{Failure, Random, Success}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by denys on 7/21/17.
  */
object FutureExample {

  def createFuture(): Future[Int] = {
    Future {
      val r = Random
      if (r.nextInt(100) % 2 == 0) 0 else throw new RuntimeException("Odd numbers are not good here")
    }
  }

  def evaluateFuture(f: Future[_]) {
    f.onComplete {
      case Success(i) => println(s"Success with $i")
      case Failure(e) => println(s"Failure with ${e.getMessage}")
    }
  }

  def main(args: Array[String]) {
    println("Start")
    evaluateFuture(createFuture())
    Thread.sleep(1000)
    evaluateFuture(createFuture())
    Thread.sleep(1000)
    evaluateFuture(createFuture())
    Thread.sleep(1000)
    println("Stop")
  }
}
