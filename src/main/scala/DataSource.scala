package main.scala

trait DataSource {

  def nextVal: Any
  def hasNext: Boolean
  def forEach(f: String => Unit)

}
