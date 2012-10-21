package test.scala

import main.data.WordCountDataSource
import main.scala.{MapperReducerJob, WordCounter, MapperReducerActor}
import org.scalatest._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.PartialFunctionValues._

class WordCounterTest extends FlatSpec with ShouldMatchers {

  "Word Counter" should "properly count words"  in {
    val source = new WordCountDataSource("hello world hello world bye world")
    val job = new MapperReducerJob[Int](source, WordCounter.emit, WordCounter.collect)
    val results = job.run

    results should have size(3)
    results.valueAt("hello") should equal(2)
    results.valueAt("world") should equal(3)
    results.valueAt("bye") should equal(1)
  }
}

