//Necessary libraries.
import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer

//Error level code.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Spark session.
val spark = SparkSession.builder.appName("LogisticRegression").getOrCreate()

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

//Training data as 70% and test data as 30%.
val Array(trainingData, testData) = dataIndexed.randomSplit(Array(0.7, 0.3))

//Logistic regression object.
val logisticReg = new LogisticRegression().setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8).setFamily("multinomial")

//Fitting the model with the training data.
val model = logisticReg.fit(trainingData)

//Making the predictions transforming the testData.
val predictions = model.transform(testData)

//Obtaining the metrics.
val predictionAndLabels = predictions.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

//Confusion matrix.
println("Confusion matrix:")
println(metrics.confusionMatrix)

//Accuracy and Test Error.
println("Accuracy: " + metrics.accuracy) 
println(s"Test Error: ${(1.0 - metrics.accuracy)}")