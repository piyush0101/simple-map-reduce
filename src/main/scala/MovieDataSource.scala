package main.scala

import java.io.File
import io.Source
import java.lang.Double

class MovieDataSource(file: File) extends DataSource{

  def forEach(f: (String, Any) => Unit) {
       for (line <- Source.fromFile(file).getLines) {
         val fields: Array[String] = line.split("\t")
         val movie = new Movie(fields(0), Double.parseDouble(fields(1)), fields(2), fields(3))
         f(fields(3), movie)
      }
  }

}
