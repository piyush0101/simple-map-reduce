package main.scala

import actors.{Actor, !}
import collection.immutable.HashMap

case class RESULTS(results: Map[String, Int])

object MapperReducerJob {
  def main(args: Array[String]) {
    val actor = new MapperReducerActor
    val job = new MapperReducerJob(actor)

    job.start()
    actor.start()
  }
}

class MapperReducerJob(actor: Actor) extends Actor {

  def act() {
    val data = "hello hello world world"

    data.split(" ").foreach(
      (x:String) => actor ! MAP(x)
    )

    actor ! REDUCE()

    loop {
      react {
        case RESULTS(results) =>
          println(results)
      }
    }
  }
}
