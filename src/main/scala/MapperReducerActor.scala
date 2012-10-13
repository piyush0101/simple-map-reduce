package main.scala

import actors.Actor
import collection.mutable

class MapperReducerActor[T]
  (f1: ((mutable.MutableList[T], Any) => mutable.MutableList[T]),
    f2: (mutable.MutableList[T] => T))
      extends Actor {

  def act() {
    var accumulator = mutable.HashMap.empty[String, mutable.MutableList[T]]
    loop {
      react {
        case MAP(key, data) =>
          var li = accumulator.getOrElse(key, new mutable.MutableList[T])
          accumulator += key -> f1(li, data)

        case REDUCE() =>
          val reduced = accumulator.mapValues((li: mutable.MutableList[T]) => f2(li))
          sender ! RESULTS(reduced)
      }
    }
  }
}
