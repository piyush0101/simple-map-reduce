package main.scala

import java.io.File
import io.Source

class MovieDataSource(f: File) {

  def forEach(f: Movie => Unit) {
      Source.fromFile(f).forEach {

      }
  }

}
