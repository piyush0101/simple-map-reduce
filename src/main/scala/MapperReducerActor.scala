package main.scala

import actors.Actor
import collection.mutable

class MapperReducerActor[T]
  (f1: ((mutable.MutableList[T]) => mutable.MutableList[T]),
    f2: (mutable.MutableList[T] => T))
      extends Actor {

  def act() {
    var accumulator = mutable.HashMap.empty[String, mutable.MutableList[T]]
    loop {
      react {
        case MAP(key) =>
          var li = accumulator.getOrElse(key, new mutable.MutableList[T])
          var accLi = f1(li)
          accumulator += key -> accLi

        case REDUCE() =>
          val reduced = accumulator.mapValues((li: mutable.MutableList[T]) => f2(li))
          sender ! RESULTS(reduced)
      }
    }
  }
}
