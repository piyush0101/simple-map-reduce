package main.data

trait DataSource {

  def forEach(f: (String, Any) => Unit)

}
