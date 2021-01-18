//1. Import a simple Spark Session.
import org.apache.spark.sql.SparkSession

//2. Use the Error Reporting code lines.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//3. Create a Spark Session instance.
val spark = SparkSession.builder.appName("KMEANS").getOrCreate()

//4. Import the KMEANS library for the clustering algorithm.
import org.apache.spark.ml.clustering.KMeans

//5. Load the "Whole Customers Data" dataset.
val dataset = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Sebas/Desktop/Wholesale customers data.csv")
dataset.show(2)

//6. Select the next columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen; and name this set "feature_data".
val  feature_data  = dataset.select("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")
feature_data.show(2)

//7. Import VectorAssembler and Vector.
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vector

//8. Create a new Vector Assembler object for the feature columns as an input set, remembering that there are no labels.
val assembler = new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features")

//9. Use the assembler object to tranform "feature_data".
val  features = assembler.transform(feature_data)
features.show(2)

//10. Create the KMEANS model with k = 3.

//10.1. Trains the k-means model.
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)

//11. Evaluate the clusters using Within Set Sum of Squared Errors (WSSSE) and print the centroids.

//11.1. Evaluate clustering by calculate Within Set Sum of Squared Errors (WSSSE).
val WSSSE = model.computeCost(features)
println(s"Within set sum of Squared Errors = $WSSSE")

//11.2. Show the centroids.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)