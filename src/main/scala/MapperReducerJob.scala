package main.scala

import actors.Actor

case class RESULTS(results: Map[String, Int])

object MapperReducerJob {
  def main(args: Array[String]) {
    val source = new WordCountDataSource("hello hello world world bye bye")
    val actor = new MapperReducerActor
    val job = new MapperReducerJob(actor, source)

    job.start()
    actor.start()
  }
}

class MapperReducerJob(actor: Actor, source: DataSource) extends Actor {

  def act() {

    source.forEach((x: String) => actor ! MAP(x))

    actor ! REDUCE()

    loop {
      react {
        case RESULTS(results) =>
          println(results)
      }
    }
  }
}
