package main.scala

import collection.mutable

object MovieRatingByYear {

  val emit = (li: mutable.MutableList[Movie], m: Any) => {
    m match {
      case m: Movie => li += m
    }
    li
  }

  val collect = (li: mutable.MutableList[Movie]) => {
    li.reduceLeft((m1:Movie, m2:Movie) => if (m1.getRating > m2.getRating) m1 else m2)
  }
}
