package main.scala

import actors.Actor
import collection.mutable

class MapperReducerActor[T]
  (emit: ((mutable.MutableList[T], Any) => mutable.MutableList[T]),
    collect: (mutable.MutableList[T] => T))
      extends Actor {

  def act() {
    var accumulator = mutable.HashMap.empty[String, mutable.MutableList[T]]
    loop {
      react {
        case MAP(key, data) =>
          val li = accumulator.getOrElse(key, new mutable.MutableList[T])
          accumulator += key -> emit(li, data)

        case REDUCE() =>
          val reduced = accumulator.mapValues((li: mutable.MutableList[T]) => collect(li))
          sender ! RESULTS(reduced)
      }
    }
  }
}
