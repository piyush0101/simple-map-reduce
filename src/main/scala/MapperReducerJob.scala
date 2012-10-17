package main.scala

import actors.Actor
import java.io.File
import main.data.{DataSource, MovieDataSource}
import collection.MapLike
import reflect.This
import collection.immutable.HashMap

case class MAP(key: String, data: Any)

case class REDUCE()

case class RESULTS(results: Any)

object MapperReducerJob {
  def main(args: Array[String]) {

    val source = new MovieDataSource(new File("movies.txt"))
    val actor = new MapperReducerActor[Movie](MovieRatingByYear.emit, MovieRatingByYear.collect)
    val job = new MapperReducerJob[Movie](actor, source)

    job.start()
    actor.start()
  }
}

class MapperReducerJob[T](actor: Actor, source: DataSource) extends Actor {

  var reduced = new HashMap[String, T]

  def act() {

    source.forEach((x: String, y: Any) => actor ! MAP(x, y))

    actor ! REDUCE()

    loop {
      react {
        case RESULTS(results) =>
          results match {
            case results: MapLike[String, T, This] =>
              results.toMap[String, T].foreach[Unit](p => reduced += p._1 -> p._2)
          }
      }
    }
  }

  def getResults: HashMap[String, T] = reduced
}
