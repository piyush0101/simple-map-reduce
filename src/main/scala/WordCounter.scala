package main.scala

import collection.mutable

object WordCounter {

  val emit = (li: mutable.MutableList[Int]) => {
    (li += 1)
    li
  }

  val collect = (li: mutable.MutableList[Int]) => {
    li.foldLeft(0)((x: Int, y: Int) => x + y)
  }

  def run =

}
