package main.scala

import actors.Actor
import collection.mutable

case class MAP(key: String)
case class REDUCE()
case class RESULTS(results: Any)

object MapperReducerJob {
  def main(args: Array[String]) {

    val source = new WordCountDataSource("hello hello world world bye bye")
    val actor = new MapperReducerActor[Int](
      ((li: mutable.MutableList[Int]) => li.),
      ((li: mutable.MutableList[Int]) => li.foldLeft(0)((x:Int, y:Int) => x+y))
    )
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
