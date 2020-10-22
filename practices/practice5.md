# PRACTICE 5 - FIFTEEN DATA FRAME FUNCTIONS IN SCALA

## Introduction

This practice was a familiarization with the functioning of the functions for data frames in scala.

## Development
 
First of all, the SparkSession and SparkImplicits imports are made to work, as well as the reading of the data frame to be used, in this case, the csv file CitiGroup2006_2008 is used.
 
	//Import and creation of the SparkSession variable
	import org.apache.spark.sql.SparkSession
	val spark = SparkSession.builder().getOrCreate()

	//SparkImplicits Import
	import spark.implicits._

	//DataFrame to use
	val df = spark.read.csv("C:/Users/Sebas/Desktop/CitiGroup2006_2008")
 
### Function 1 - count()
 
This Function is used to count the number of elements that make up the data frame.
 
	//1. Function count()
	df.count() //Counts the elements of the data frame.
 
### Function 2 - first()
 
This Function serves to bring the first row of the data frame.
 
	//2. Function first()
	df2 = df.first() //Shows the first row of the data frame.
	print(df2)

### Function 3 - filter()
 
This Function is used to filter according to the given condition. In this example, only items in column c1 that are exactly equal to 490 are displayed.
 
	//3. Function filter()
	df.filter($"_c1"===490).show() //Filter by condition.
 
### Function 4 - groupBy()
 
This Function is used according to the column indicated in the data frame.
 
	//4. Function groupBy()
	df.groupBy($"_c1").count().show() //Group according to column.
 
### Function 5 - dtypes()
 
This Function serves to indicate the type of data that the selected column of the data frame presents.
 
	//5. Function dtypes()
	df.dtypes(0) //Indicates the data type of the column.
 
### Function 6 - drop()
 
This Function serves to discard the indicated column of the data frame.
 
	//6. Function drop()
	df.drop($"_c0").show() //Discard the indicated column.
 
### Function 7 - limit()
 
This Function serves to limit to a number n the first rows that are shown of the data frame.
 
	//7. Function limit()
	df.limit(10).show() //Limit first rows to "n".

### Function 8 - sort()
 
This Function is used to return a new data frame ordered according to the specified column in ascending order.
 
	//8. Function sort()
	df.sort($"_c5").show() //New data frame sorted according to the specified column in ascending order.
 
### Function 9 - withColumnRenamed()
 
This Function is used to assign a new name to the provided column of the data frame, renaming it with the second value given.
 
	//9. Function withColumnRenamed()
	df.withColumnRenamed("_c0", "Fecha").show() //Rename a column.
 
### Function 10 - select()
 
This Function is used to select the elements of the given column of the data frame.
 
	//10. Function select()
	df.select($"_c2").show() //Selection of elements in the indicated column.
 
### Function 11 - repartition()
 
This Function serves to return a new data frame partitioned by the indicated.
 
	//11. Function repartition()
	df.repartition(10, $"_c1").show(800) //Returns a new partitioned data frame.
 

### Function 12 - dropDuplicate()
 
This Function serves to return columns without duplicate rows of the data frame.
 
	//12. Function dropDuplicates()
	df.dropDuplicates("_c0").show() //Returns a new data frame but without duplicate rows from the given columns.
 
### Function 13 - describe()
 
This Function is used to obtain numerical statistics of the given data frame column, such as the total, the average, the minimum, the maximum, etc.
 
	//13. Function describe()
	df.describe("_c1").show() //Returns numerical statistics for the column such as total, mean, minimum, maximum, etc.
 
### Function 14 - as()
 
This Function is used to return a data frame with an alias.
 
	//14. Function as()
	df.as("Apodo").show() //Returns a dataset with an alias.
 
### Function 15 - sample()
 
This Function serves to return a data frame with a random sample of the original using a seed.
 
	//15. Function sample()
	df.sample(true, 2).show() //Returns a random sample from the original dataset with a seed.