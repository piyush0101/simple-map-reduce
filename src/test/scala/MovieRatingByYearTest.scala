package test.scala

import main.data.{MovieDataSource, WordCountDataSource}
import main.scala._
import org.scalatest._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.PartialFunctionValues._
import java.io.File


class MovieRatingByYearTest extends FlatSpec with ShouldMatchers {

  "MovieCounter" should "give back ShawshankRedemption as the highest rated movie for year 1994" in {
    val source = new MovieDataSource(new File("movies.txt"))
    val job = new MapperReducerJob[Movie](source, MovieRatingByYear.emit, MovieRatingByYear.collect)
    val results = job.run

    results should have size (70)
    results.valueAt("1994") should equal (new Movie("614692", 9.2, "The Shawshank Redemption", "1994"))
  }

}
