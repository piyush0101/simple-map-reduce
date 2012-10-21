package main.scala

import actors.Actor
import java.io.File
import main.data.{WordCountDataSource, DataSource, MovieDataSource}
import collection.MapLike
import reflect.This
import collection.immutable.HashMap
import javax.xml.ws.Response

case class MAPREDUCE(source: DataSource)

class MapperReducerJob[T](actor: Actor, source: DataSource) {
  def run: Map[String, T] = {
    actor.start()
    var reduced = new HashMap[String, T]
    val results = actor !! MAPREDUCE(source)
    results.inputChannel.receive {
      case msg: MapLike[String, T, This] => msg.foreach(p => reduced += p._1 -> p._2)
    }
    reduced
  }
}
