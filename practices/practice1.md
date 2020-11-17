# PRACTICE 1 - LINEAR REGRESSION

In this practice, we followed a instruction series were we implemented functions of linear regression ir order to obtain results, based in the Clean Ecommerce data frame.

## DEVELOPMENT

### 1. LINEAR REGRESSION LIBRARY

We import the linear regression library with the code:

	// Import LinearRegression
	import org.apache.spark.ml.regression.LinearRegression

And we use the next code to config the error level.

	// Opcional: Utilice el siguiente codigo para configurar errores
	import org.apache.log4j._
	Logger.getLogger("org").setLevel(Level.ERROR)

### 2. SPARK SESSION

We start a spark session in order to import the data later.

	// Inicie una simple Sesion Spark
	import org.apache.spark.sql.SparkSession
	
	val spark = SparkSession.builder().appName("LinearRegressionAssigment").getOrCreate()

### 3. DATA IMPORTATION

We import the data and we save it in the _cleanecommerce_ value.

	// Utilice Spark para el archivo csv Clean-Ecommerce.
	val path = "C:/Users/Sebas/Desktop/PC/ESCUELA/9. NOVENO SEMESTRE/2. Datos masivos/DATOS MASIVOS (PROFESOR)/BigData/Spark_Regression/Clean-Ecommerce.csv"
	
	val cleanecommerce = spark.read.option("header", "true").option("inferSchema","true")csv(path)

### 4. DATA FRAME SCHEMA

We print the data frame schema and show the first row to see if the importation was successful.

	// Imprima el schema en el DataFrame.
	cleanecommerce.printSchema()

	// Imprima un renglon de ejemplo del DataFrane.
	cleanecommerce.show(1)

### 5. VECTORS AND VECTOR ASSEMBLER

We import Vectors and VectorAssembler with the code below:

	// Importe VectorAssembler y Vectors
	import org.apache.spark.ml.feature.VectorAssembler
	import org.apache.spark.ml.linalg.Vectors

### 6. DATA FRAME DF

We create a new data frame from the _cleanecommerce_ data frame. This new data frame is called _df_, with the column _Yearly Amount Spent_ as _label_ and only numeric columns.

	// Renombre la columna Yearly Amount Spent como "label"
	// Tambien de los datos tome solo la columa numerica.
	// Deje todo esto como un nuevo DataFrame que se llame df
	val df = cleanecommerce.select($"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership", $"Yearly Amount Spent".as("label"))

### 7. ASSEMBLER VALUE

We create the value _assembler_ with VectorAssembler() function. We use this object in order to create the columns of _df_ in one only column called _features_.

	// Que el objeto assembler convierta los valores de entrada a un vector
	// Utilice el objeto VectorAssembler para convertir la columnas de entradas del df
	// a una sola columna de salida de un arreglo llamado  "features"
	// Configure las columnas de entrada de donde se supone que leemos los valores.
	// Llamar a esto nuevo assambler.
	val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App", "Time on Website", "Length of Membership")).setOutputCol("features")