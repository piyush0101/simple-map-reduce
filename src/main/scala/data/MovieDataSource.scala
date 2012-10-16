package main.data

import java.io.File
import io.Source
import main.scala.Movie
import java.lang.Double

class MovieDataSource(file: File) extends DataSource {

  def forEach(f: (String, Any) => Unit) {
       for (line <- Source.fromFile(file).getLines) {
         val fields: Array[String] = line.split("\t")
         val id = fields(0)
         val name = fields(2)
         val rating = Double.parseDouble(fields(1))
         val year = fields(3)
         val movie = new Movie(id, rating, name, year)
         f(year, movie)
      }
  }

}
