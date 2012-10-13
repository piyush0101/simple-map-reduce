package main.scala

import actors.Actor
import java.io.File
import main.data.{MovieDataSource, DataSource}

case class MAP(key: String, data:Any)

case class REDUCE()

case class RESULTS(results: Any)

object MapperReducerJob {
  def main(args: Array[String]) {

    val source = new MovieDataSource(new File("movies.txt"))
    val actor = new MapperReducerActor[Movie](MovieRatingByYear.emit, MovieRatingByYear.collect)
    val job = new MapperReducerJob(actor, source)

    job.start()
    actor.start()
  }
}

class MapperReducerJob(actor: Actor, source: DataSource) extends Actor {

  def act() {

    source.forEach((x: String, y:Any) => actor ! MAP(x,y))

    actor ! REDUCE()

    loop {
      react {
        case RESULTS(results) =>
          println(results)
          System.exit(0)
      }
    }
  }
}
