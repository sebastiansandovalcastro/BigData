# PRACTICE 4 - DECISION TREES (TEAM 2 - CODE ANALYSIS)

In this practice, we analyzed the team 2 code: _Decision Trees_.

## DEVELOPMENT

First, they import the necessary libraries, in this case, to make use of pipelines, decision trees classification models, decision trees classifiers, multiclass classification evaluators, string indexers and vector indexers, importing SparkSession too.

Then they create an object called "_DecisionTree_" with a main function inside with the code of the program.

They create the spark value with the spark session in it and the data value with the information in "_sample_libsvm_data.txt_".

After importing the data, they start to put index labels, adding metadata to the label column and fitting the whole data set to include all labels on the index. Then, automatically, they identify categorical features and then index them.

Later, they split the data into training and test sets (30% held out for testing), make the decision tree model and convert indexed labels back to original labels, chaining the indexers and tree in a Pipeline.

The team train the model and also runs the indexers in the same code. They make their predictions, select a few columns as an example, create an evaluator and use that value to calculate the error.

Finally, they show the results.

```scala
// Importing this libraries is required in order to get the example done.
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

// Start a simple spark session
import org.apache.spark.sql.SparkSession

//val spark = SparkSession.builder().getOrCreate()

object DecisionTree {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("dtree")
      .getOrCreate()

// Load the data stored in LIBSVM format as a DataFrame.
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")

// Index labels, adding metadata to the label column.
// Fit on whole dataset to include all labels into the index.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
// Automatically identify categorical features and then index them.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

// Split the data into training and test sets (30% held out for testing).
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

// Train a DecisionTree model.
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// Convert indexed labels back to original labels.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Chain indexers and tree in a Pipeline.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

// Train the model, this also runs the indexers.
val model = pipeline.fit(trainingData)

// Make the predictions.
val predictions = model.transform(testData)

// Select example rows to display. In this case there was only 5 rows to show.
predictions.select("predictedLabel", "label", "features").show(5)

// Select (prediction, true label)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
// Compute the test error.
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

// Show by stages the classification of the tree model
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")

  }
}

// Preview of the last lines output
/*  +--------------+-----+--------------------+
    |predictedLabel|label|            features|
    +--------------+-----+--------------------+
    |           1.0|  0.0|(692,[122,123,124...|
    |           0.0|  0.0|(692,[122,123,148...|
    |           0.0|  0.0|(692,[123,124,125...|
    |           1.0|  0.0|(692,[124,125,126...|
    |           0.0|  0.0|(692,[126,127,128...|
    |           0.0|  0.0|(692,[126,127,128...|
    |           0.0|  0.0|(692,[126,127,128...|
    |           0.0|  0.0|(692,[127,128,129...|
    |           1.0|  0.0|(692,[129,130,131...|
    |           0.0|  0.0|(692,[152,153,154...|
    +--------------+-----+--------------------+
    only showing top 10 rows
    Test Error = 0.18518518518518523
    Learned classification tree model:
    DecisionTreeClassificationModel (uid=dtc_b395cba8d741) of depth 2 with 5 nodes
      If (feature 351 <= 30.5)
      If (feature 126 <= 253.5)
        Predict: 1.0
      Else (feature 126 > 253.5)
        Predict: 0.0
      Else (feature 351 > 30.5)
      Predict: 0.0
  */
```
