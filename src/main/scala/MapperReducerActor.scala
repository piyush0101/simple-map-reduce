package main.scala

import actors.Actor
import collection.mutable.MutableList
import collection.mutable

class MapperReducerActor[T]
  (f1: ((mutable.MutableList[T]) => mutable.MutableList[T]),
    f2: (mutable.MutableList[T] => T))
      extends Actor {

  def act() {
    loop {
      var accumulator = mutable.HashMap.empty[String, MutableList[T]]
      react {
        case MAP(key) =>
          accumulator += key -> f1(accumulator.getOrElse(key, new mutable.MutableList[T]))

        case REDUCE() =>
          val reduced = accumulator.mapValues((li: MutableList[T]) => f2(li))
          sender ! RESULTS(reduced)
      }
    }
  }
}
