# PRACTICE 2 - LOGISTIC REGRESSION

In this practice, we analyzed the code of the _"PracticaLogisticRegression.scala"_ and we commented each line in order to explain what is happening.

## DEVELOPMENT

### IMPORTING THE DATA

First we import all the necessary libraries. We create the "SparkSession", we load the data and we print de schema.

	// Importing the sparksession and the logistic regression libraries.
	import org.apache.spark.ml.classification.LogisticRegression
	import org.apache.spark.sql.SparkSession
	
	// Using the error reporting code.
	import org.apache.log4j._
	Logger.getLogger("org").setLevel(Level.ERROR)
	
	// Creating a spark session.
	val spark = SparkSession.builder().getOrCreate()
	
	// Using the spark session created before to read the advertising.csv file.
	val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("advertising.csv")
	
	// Printing the schema.
	data.printSchema()

### SHOWING THE DATA

We print a example with the next code, in order to see the complete first row.

	// Printing a row.
	data.head(1)
	
	//Printing the first row.
	val colnames = data.columns
	val firstrow = data.head(1)(0)
	println("\n")
	println("Example data row")
	for(ind <- Range(1, colnames.length)){
		println(colnames(ind))
		println(firstrow(ind))
		println("\n")
	}

### PREPARING THE DATA FRAME

We prepare the data frame for use the functions of logistic regression, of machine learning. We import another necessary libraries and we create the assembler value.

	//Creating a new column called "Hour" from the Timestamp containing the "Hour of the click".
	val timedata = data.withColumn("Hour",hour(data("Timestamp")))
	
	//Renaming the column "Clicked on Ad" to "label".
	//Taking the following columns as features "Daily Time Spent on Site", "Age", "Area Income", "Daily Internet Usage", "Timestamp", "Male".
	val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male")
	
	//Importing VectorAssembler y Vectors.
	import org.apache.spark.ml.feature.VectorAssembler
	import org.apache.spark.ml.linalg.Vectors
	
	//Creating a new VectorAssembler object called assembler for the features.
	val assembler = (new VectorAssembler().setInputCols(Array("Daily Time Spent on Site","Age","Area Income","Daily Internet Usage","Hour","Male")).setOutputCol("features"))
	
	//Using randomSplit() function to create 70/30 split test and train data.
	val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)