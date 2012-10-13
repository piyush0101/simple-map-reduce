package main.scala


class WordCountDataSource(data: String) extends DataSource {

  val SPACE = " "

  def forEach(f: (String, Any) => Unit) {
    data.split(SPACE).foreach((x: String) => f(x, x))
  }

}
