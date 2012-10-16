package test.scala
import main.data.WordCountDataSource
import main.scala.{MapperReducerJob, WordCounter, MapperReducerActor}

class WordCounterTest extends FunSuite {

  test ("word counter properly counts words") {
    val source = new WordCountDataSource("hello world hello world bye world")
    val actor = new MapperReducerActor(WordCounter.emit, WordCounter.collect)

    val job = new MapperReducerJob(actor, source)

    job.start()
    actor.start()

    assert(job.getResults.get("hello").equals(2))
  }

}
