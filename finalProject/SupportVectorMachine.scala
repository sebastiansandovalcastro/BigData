//Necessary libraries.
import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler

//Error level code.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Spark session.
val spark = SparkSession.builder.appName("SVM").getOrCreate()

//Reading the csv file.
val df  = spark.read.option("header","true").option("inferSchema", "true").option("delimiter",";").format("csv").load("C:/Users/Sebas/Desktop/unit4/bank.csv")

//Indexing.
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(df)
val indexed = labelIndexer.transform(df).drop("y").withColumnRenamed("indexedLabel", "label")

//Vector of the numeric category columns.
val vectorFeatures = (new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features"))

//Transforming the indexed value.
val features = vectorFeatures.transform(indexed)

//Renaming the column y as label.
val featuresLabel = features.withColumnRenamed("y", "label")

//Union of label and features as dataIndexed.
val dataIndexed = featuresLabel.select("label","features")

//Creation of labelIndexer and featureIndexer for the pipeline, Where features with distinct values > 4, are treated as continuous.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(dataIndexed)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(dataIndexed)

//Training data as 70% and test data as 30%.
val Array(training, test) = dataIndexed.randomSplit(Array(0.7, 0.3))

//Linear Support Vector Machine object.
val supportVM = new LinearSVC().setMaxIter(10).setRegParam(0.1)
    
//Fitting the trainingData into the model.
val model = supportVM.fit(trainingData)

//Transforming testData for the predictions.
val predictions = model.transform(testData)

//Obtaining the metrics.
val predictionAndLabels = predictions.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

//Confusion matrix.
println("Confusion matrix:")
println(metrics.confusionMatrix)

//Accuracy and Test Error.
println("Accuracy: " + metrics.accuracy) 
println(s"Test Error = ${(1.0 - metrics.accuracy)}")