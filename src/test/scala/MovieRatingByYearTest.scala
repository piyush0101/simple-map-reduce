package test.scala

import main.data.{MovieDataSource, WordCountDataSource}
import main.scala._
import org.scalatest._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.PartialFunctionValues._
import java.io.File


class MovieRatingByYearTest extends FlatSpec with ShouldMatchers {

  "ShawshankRedemption" should "be the highest rated movie for year 1994" in {
    val source = new MovieDataSource(new File("movies.txt"))
    val actor = new MapperReducerActor[Movie](MovieRatingByYear.emit, MovieRatingByYear.collect)

    val job = new MapperReducerJob[Movie](actor, source)
    val results = job.run

  }

}
