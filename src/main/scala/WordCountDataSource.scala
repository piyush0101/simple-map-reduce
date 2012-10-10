package main.scala

class WordCountDataSource(data: String) extends DataSource {

  def forEach(f: String => Unit) {
    data.split(" ").foreach((x:String) => f(x))
  }

}
