package main.scala

import actors.Actor
import collection.immutable.HashMap
import collection.mutable.MutableList

case class MAP(value: String)

case class REDUCE()

class MapperReducerActor extends Actor {

  def act() {
    var accumulator = HashMap.empty[String, MutableList[Int]]
    loop {
      react {
        case MAP(word) =>
          var list = accumulator.getOrElse(word, new MutableList[Int]())
          accumulator += word -> (list += 1)

        case REDUCE() =>
          val reduced = accumulator.mapValues((li: MutableList[Int]) => li.foldLeft(0)((x:Int, y:Int) => x+y))
          sender ! RESULTS(reduced)
      }
    }
  }
}
