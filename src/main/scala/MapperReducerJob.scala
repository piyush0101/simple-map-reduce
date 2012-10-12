package main.scala

import actors.Actor

case class MAP(key: String)

case class REDUCE()

case class RESULTS(results: Any)

object MapperReducerJob {
  def main(args: Array[String]) {

    val source = new WordCountDataSource("hello hello world world bye bye")
    val actor = new MapperReducerActor[Int](WordCounter.emit, WordCounter.collect)
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
