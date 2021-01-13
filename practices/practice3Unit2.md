# PRACTICE 3 - BASIC STATISTICS (TEAM 1 - CODE ANALYSIS)

In this practice, we analyzed the team 1 codes: _Correlation_, _Chi Square_ and _Summarizer_.

## CORRELATION

First, they import the necessary libraries, in this case, to make use of matrices, vectors, rows (for matrices), make correlations and use a spark session.

Then, they start creating and object called "_CorrelationExample_", using it to run the program just calling it by this name.

They proceed to define a main function, where they initialize a spark session in the _spark_ value, importing spark implicits too.

After that, they create a _data_ value as an example of information, creating a data frame using that value and finalizing with the Pearson's and Spearman's correlations.

```scala
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession

object CorrelationExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("CorrelationExample")
      .getOrCreate()
    import spark.implicits._

    val data = Seq(
      Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
      Vectors.dense(4.0, 5.0, 0.0, 3.0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0),
      Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
    )

    val df = data.map(Tuple1.apply).toDF("features")
    val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
    println(s"Pearson correlation matrix:\n $coeff1")

    val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2")

    spark.stop()
  }
}
```

## CHI SQUARE

First, they import the necessary libraries, in this case, to make use of vectors, Chi Square Tests and use a spark session.

Then, they start creating and object called "_ChiSquareTestExample_", using it to run the program just calling it by this name.

They proceed to define a main function, where they initialize a spark session in the _spark_ value, importing spark implicits too.

After that, they create a _data_ value as an example of information, creating a data frame using that value in two columns and finalizing with the pValues, the degrees of freedom and the statistics.

```scala
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.ChiSquareTest
import org.apache.spark.sql.SparkSession

object ChiSquareTestExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("ChiSquareTestExample")
      .getOrCreate()
    import spark.implicits._

    val data = Seq(
      (0.0, Vectors.dense(0.5, 10.0)),
      (0.0, Vectors.dense(1.5, 20.0)),
      (1.0, Vectors.dense(1.5, 30.0)),
      (0.0, Vectors.dense(3.5, 30.0)),
      (0.0, Vectors.dense(3.5, 40.0)),
      (1.0, Vectors.dense(3.5, 40.0))
    )

    val df = data.toDF("label", "features")
    val chi = ChiSquareTest.test(df, "features", "label").head
    println(s"pValues = ${chi.getAs[Vector](0)}")
    println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
    println(s"statistics ${chi.getAs[Vector](2)}")

    spark.stop()
  }
}
```

## SUMMARIZER

First, they import the necessary libraries, in this case, to make use of vectors, Summarizer and use a spark session.

Then, they start creating and object called "_SummarizerExample_", using it to run the program just calling it by this name.

They proceed to define a main function, where they initialize a spark session in the _spark_ value, importing spark implicits and Summarizer too.

After that, they create a _data_ value as an example of information, creating a data frame using that value in two columns, the mean and variance (first values with Summary) and the mean and variance (second values without Summary).

```scala
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.Summarizer
import org.apache.spark.sql.SparkSession

object SummarizerExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("SummarizerExample")
      .getOrCreate()

    import spark.implicits._
    import Summarizer._

    val data = Seq(
      (Vectors.dense(2.0, 3.0, 5.0), 1.0),
      (Vectors.dense(4.0, 6.0, 7.0), 2.0)
    )

    val df = data.toDF("features", "weight")

    val (meanVal, varianceVal) = df.select(metrics("mean", "variance")
      .summary($"features", $"weight").as("summary"))
      .select("summary.mean", "summary.variance")
      .as[(Vector, Vector)].first()

    println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")

    val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
      .as[(Vector, Vector)].first()

    println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")

    spark.stop()
  }
}
```
