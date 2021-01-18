//Importing the necessary libraries.
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.log4j._

//Creating the "Error Reporting" code.
Logger.getLogger("org").setLevel(Level.ERROR)

//Creating the SparkSession
val spark = SparkSession.builder.appName("MultilayerPerceptron").getOrCreate()

//Loading the csv file.
val irisdf = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Sebas/Desktop/iris.csv")

//Showing the columns names.
irisdf.columns

//Printing the schema.
irisdf.printSchema()

//Printing the first five columns.
irisdf.show(5)

//Describing the dataframe.
irisdf.describe().show()

//Converting the data to categorical data and describing the dataset to see the changes (column species replaced by column label).
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(irisdf)
val indexed = labelIndexer.transform(irisdf).drop("species").withColumnRenamed("indexedLabel", "label")
indexed.describe().show()

//Making "sepal_length", "sepal_width", "petal_length", "petal_width" a vector to work with numeric data (features).
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
val features = assembler.transform(indexed)

//Indexing tags while we adding metadata to the tag column, fitting the entire dataset to include all the tags in the index.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(indexed)
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")
features.show()

//Preparing the training set and the test set (training - 70%, test - 30% and seed - 123)
val splits = features.randomSplit(Array(0.7, 0.3), seed = 123)
val train = splits(0)
val test = splits(1)

//Specifying the layers for neural network:
//Input layer (features) - 4
//Intermediate layers (hidden layers) - 5 and 4.
//Output layer (classes) - 3.
val layers = Array[Int](4, 5, 4, 3)

//Creating the trainer and setting the parameters (layers, block size - 128, seed - 123, max iteration - 100).
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(123).setMaxIter(100)

//Training the model.
val model = trainer.fit(train)

//Accuracy on the test set.
//Calculating the precision in the test set.
//Evaluating the model for prediction.
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

//Printing the test set accuracy.
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")