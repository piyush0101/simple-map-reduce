package main.scala

trait DataSource {

  def forEach(f: (String, Any) => Unit)

}
