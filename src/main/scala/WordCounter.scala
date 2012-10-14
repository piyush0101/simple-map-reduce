package main.scala

import collection.mutable

object WordCounter {

  val emit = (li: mutable.MutableList[Int], i: Any) => {
    li += 1
  }

  val collect = (li: mutable.MutableList[Int]) => {
    li.foldLeft(0)((x: Int, y: Int) => x + y)
  }

}
