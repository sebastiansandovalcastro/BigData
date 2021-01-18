# EVALUATION UNIT 2

<div align="center">

**TECNOLÓGICO NACIONAL DE MÉXIO**

INSTITUTO TECNOLÓGICO DE TIJUANA

SUBDIRECCIÓN ACADÉMICA
 
DEPARTAMENTO DE SISTEMAS Y COMPUTACIÓN
 
SEMESTRE SEPTIEMBRE 2020 – ENERO 2021

INGENIERÍA EN SISTEMAS COMPUTACIONALES

 
 [![](https://upload.wikimedia.org/wikipedia/commons/2/2e/ITT.jpg)](https://upload.wikimedia.org/wikipedia/commons/2/2e/ITT.jpg)

**MASTER**

JOSÉ CHRISTIAN ROMERO HERNÁNDEZ

**CLASS**

BIG DATA
BDD-1704 SC9A, L - V 18:00 - 19:00 (91L4/0308)


**EVALUATION UNIT 2**


**STUDENT**

PACHECO RAMÍREZ HUGO ANDRÉS	16210790

SANDOVAL CASTRO SEBASTIÁN	16212076


Tijuana, Baja California, december 15, 2020.

</div>

## DEVELOPMENT

### 1. IMPORTING THE DATA

We import all the necessary libraries in order to work with the data, using the Multilayer Perceptron Classifier.

```sacala
//Importing the necessary libraries.
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.log4j._
```

We create the "Error Reporting" code.

```scala
//Creating the "Error Reporting" code.
Logger.getLogger("org").setLevel(Level.ERROR)
```

Then we create the spark session.

```scala
//Creating the SparkSession
val spark = SparkSession.builder.appName("MultilayerPerceptron").getOrCreate()
```

And finally we import de iris.csv data in a dataframe called "irisdf".

```scala
//Loading the csv file.
val irisdf = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Sebas/Desktop/iris.csv")
```

### 2. COLUMN NAME

We use the next code to see the irisdf columns names: sepal_length, sepal_width, petal_length, petal_width, species.

```scala
//Showing the columns names.
irisdf.columns
```

### 3. THE SCHEMA

We proceed to print the schema to learn more about the columns features.

```scala
//Printing the schema.
irisdf.printSchema()
```

### 4. THE FIRST FIVE COLUMNS

We show the first five columns with the first five rows in order to see how the data frame is distributed.

```scala
//Printing the first five columns.
irisdf.show(5)
```

### 5. DESCRIBING THE DATA FRAME

We print count, mean, stddev, min and max by column.

```scala
//Describing the dataframe.
irisdf.describe().show()
```

### 6. TRANSFORMING THE DATA

Here we covert the data to categorical data, changing the column species for the new indexed column called label.

```scala
//Converting the data to categorical data and describing the dataset to see the changes (column species replaced by column label).
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(irisdf)
val indexed = labelIndexer.transform(irisdf).drop("species").withColumnRenamed("indexedLabel", "label")
indexed.describe().show()
```

Here we making "sepal_length", "sepal_width", "petal_length", "petal_width" a vector to work with numeric data (features).

```scala
//Making "sepal_length", "sepal_width", "petal_length", "petal_width" a vector to work with numeric data (features).
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
val features = assembler.transform(indexed)
```

And here we index tags while we add metadata to the tag column, fitting the entire dataset to include all the tags in the index.

```scala
//Indexing tags while we adding metadata to the tag column, fitting the entire dataset to include all the tags in the index.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(indexed)
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")
features.show()
```

### 7. BUILDING THE MODEL

We prepared the training set and the test set making a split where 70 percent were destinated for the training and the 30 percet for the test, with a random seed.

```scala
//Preparing the training set and the test set (training - 70%, test - 30% and seed - 123)
val splits = features.randomSplit(Array(0.7, 0.3), seed = 123)
val train = splits(0)
val test = splits(1)
```

We specify the layers in a integer vector for the Multilayer Perceptron Classifier, for the neural network. We put the input layer with a 4 as value, a hidden layers with 5 and 4 as values, and an output of 3.

```scala
//Specifying the layers for neural network:
//Input layer (features) - 4
//Intermediate layers (hidden layers) - 5 and 4.
//Output layer (classes) - 3.
val layers = Array[Int](4, 5, 4, 3)
```

Here, we create trainer with the layers made before, with a block size of 128, a random seed and a max iteration 100.

```scala
//Creating the trainer and setting the parameters (layers, block size - 128, seed - 123, max iteration - 100).
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(123).setMaxIter(100)
```

We fit the trainer in the train data (30%) in order to train the model.

```scala
//Training the model.
val model = trainer.fit(train)
```

We transform the model with the test data and save it as the result, we calculate the precision with the predictions and then, we evaluate the model for prediction, with the accuracy as result.

```scala
//Accuracy on the test set.
//Calculating the precision in the test set.
//Evaluating the model for prediction.
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```

### 8. PRINTING RESULTS

We print the results in order to see the test set accuracy.

```scala
//Printing the test set accuracy.
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```

![result.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit2/evaluation/result.png)