# EVALUATION UNIT 3

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


**EVALUATION UNIT 3**


**STUDENT**

PACHECO RAMÍREZ HUGO ANDRÉS	16210790

SANDOVAL CASTRO SEBASTIÁN	16212076


Tijuana, Baja California, december 18, 2020.

</div>

## BEFORE THE START

Instructions: _Develop the following instructions in Spark with the Scala programming language._

Objective: _The goal of this hands-on test is to try to group customers from specific regions of a wholesaler. This based on the sales of some product categories._

## DEVELOPMENT

### 1. Import a simple Spark Session.

```scala
//1. Import a simple Spark Session.
import org.apache.spark.sql.SparkSession
```

![1.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/1.png)

### 2. Use the Error Reporting code lines.

```scala
//2. Use the Error Reporting code lines.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

![2.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/2.png)

### 3. Create a Spark Session instance.

```scala
//3. Create a Spark Session instance.
val spark = SparkSession.builder.appName("KMEANS").getOrCreate()
```

![3.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/3.png)

### 4. Import the KMEANS library for the clustering algorithm.

```scala
//4. Import the KMEANS library for the clustering algorithm.
import org.apache.spark.ml.clustering.KMeans
```

![4.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/4.png)

### 5. Load the "Whole Customers Data" dataset.

```scala
//5. Load the "Whole Customers Data" dataset.
val dataset = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Sebas/Desktop/Wholesale customers data.csv")
dataset.show(2)
```

![5.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/5.png)

### 6. Select the next columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen; and name this set "feature_data".

```scala
//6. Select the next columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen; and name this set "feature_data".
val  feature_data  = dataset.select("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")
feature_data.show(2)
```

![6.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/6.png)

### 7. Import VectorAssembler and Vector.

```scala
//7. Import VectorAssembler and Vector.
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vector
```

![7.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/7.png)

### 8. Create a new Vector Assembler object for the feature columns as an input set, remembering that there are no labels.

```scala
//8. Create a new Vector Assembler object for the feature columns as an input set, remembering that there are no labels.
val assembler = new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features")
```

![8.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/8.png)

### 9. Use the assembler object to tranform "feature_data".

```scala
//9. Use the assembler object to tranform "feature_data".
val  features = assembler.transform(feature_data)
features.show(2)
```

![9.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/9.png)

### 10. Create the KMEANS model with k = 3.

```scala
//10. Create the KMEANS model with k = 3.

//10.1. Trains the k-means model.
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)
```

![10.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/10.png)

### 11. Evaluate the clusters using Within Set Sum of Squared Errors (WSSSE) and print the centroids.

```scala
//11. Evaluate the clusters using Within Set Sum of Squared Errors (WSSSE) and print the centroids.

//11.1. Evaluate clustering by calculate Within Set Sum of Squared Errors (WSSSE).
val WSSSE = model.computeCost(features)
println(s"Within set sum of Squared Errors = $WSSSE")

//11.2. Show the centroids.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)
```

![11.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit3/evaluation/11.png)