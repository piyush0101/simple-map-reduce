package main.scala

class WordCountDataSource(data: String) extends DataSource {

  val tokenizedSource = data.split(" ").toList.iterator

  def hasNext : Boolean = tokenizedSource.hasNext
  def nextVal : String = tokenizedSource.next()

  def forEach(f: String => Unit) {
    while (tokenizedSource.hasNext) {
      f(nextVal)
    }
  }
}
