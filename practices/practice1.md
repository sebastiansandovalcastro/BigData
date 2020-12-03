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
	
### 8. DATA FRAME DF2

We transform the data frame _df_ with the transform() function, using the assembler created before. We use the union between the column that we called _label_ and the new column called _features_. We save the result in the new data frame _df2_.

	// Transforme el data frame para que tome la forma de ("label","features")
	// Utilice el assembler para transform nuestro DataFrame a dos columnas: label and features
	val df2 = assembler.transform(df).select($"label", $"features")

### 9. LINEAR REGRESSION

We create an object called _lr_, that represents a new LinearRegression() object.

	// Crear un objeto para modelo de regresion linea.
	val lr = new LinearRegression()

We fit the _df2_ data frame using the _lr_ object created before and saving the result in the _lrModelo_ value.

	// Ajuste el modelo para los datos y llame a este modelo lrModelo
	val lrModelo = lr.fit(df2)

And finally, we make a summary of the model creating an object called _trainingSummary_ with the _summary_ function, and using this object to obtain the results that we were looking for.

	// Resuma el modelo sobre el conjunto de entrenamiento imprima la salida de algunas metricas!
	// Utilize metodo .summary de nuestro  modelo para crear un objeto
	// llamado trainingSummary
	val trainingSummary = lrModelo.summary

### 10. RESULTS

We show the cofficients and the intercept for the linear regression using _lrModelo_.

	// Imprima the coefficients e intercept para la regresion lineal
	println(s"Coefficients: ${lrModelo.coefficients} Intercept: ${lrModelo.intercept}")

![1.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit2/practice1/1.png)

And we print the residuals, the root meand squared error (RMSE), the mean squared error (MSE) and the r².

	// Muestre los valores de residuals, el RMSE, el MSE, y tambien el R^2 .
	trainingSummary.residuals.show()

![2.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit2/practice1/2.png)

	println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
	println(s"MSE: ${trainingSummary.meanSquaredError}")
	println(s"r2: ${trainingSummary.r2}")

![3.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit2/practice1/3.png)