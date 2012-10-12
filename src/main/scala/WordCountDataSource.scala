package main.scala

import collection.mutable

class WordCountDataSource(data: String) extends DataSource {

  val SPACE = " "

  def forEach(f: String => Unit) {
    data.split(SPACE).foreach((x: String) => f(x))
  }

}
