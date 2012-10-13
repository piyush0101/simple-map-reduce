package main.scala

class Movie(id: String, rating: java.lang.Double, name: String, year: String) {

  override def toString = name

  def getId = id

  def getRating = rating

  def getName = name

  def getYear = year

  override def equals(obj: Any): Boolean = {
    obj match {
      case obj: Movie =>
        this.id.equals(obj.getId) &&
          this.getName.equals(obj.getName) &&
          this.getYear.equals(obj.getYear) &&
          this.getRating.equals(obj.getRating)
      case _ => false
    }
  }

  override def hashCode(): Int = {
    var hash = 7
    hash = 31 * hash + id.length
    hash = 31 * hash + rating.intValue()
    hash = 31 * hash + name.length
    hash = 31 * hash + year.length
    hash
  }
}
