//EVALUATION UNIT 1

//DEVELOPMENT

//1. STARTING A SPARK SESSION
//We import SparkSession and create the val spark to generate the session. We will use this value to import the CSV file.
	
    //We import SparkSession.
	import org.apache.spark.sql.SparkSession

	//We create a value called spark and also We create the SparkSession inside the value.
	val spark = SparkSession.builder().getOrCreate()

//2. LOADING A FILE AND INFERRING THE DATA TYPES
//Using the spark value, We import the CSV file called "Netflix_2011_2016.csv" with the options "header" and "inferSchema" activated. Then, We show the data frame titled "netflixDF".

	//We import the CSV file called "Netflix_2011_2016.csv", with the inferSchema and the header activated.
	val netflixDF = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Sebas/Desktop/EXAMEN DATOS/Netflix_2011_2016.csv")

	//We show the data frame netflixDF.
	netflixDF.show(5)

//3. SHOWING COLUMNS NAMES
//First We see all the columns of the data frame and then We can print each column name using the position.

	//We see the columns of the data frame.
	netflixDF.columns

	//We print the name of the first column.
	netflixDF.columns(0)

	//We print the name of the second column.
	netflixDF.columns(1)

	//We print the name of the third column.
	netflixDF.columns(2)

	//We print the name of the fourth column.
	netflixDF.columns(3)

	//We print the name of the fifth column.
	netflixDF.columns(4)

	//We print the name of the sixth column.
	netflixDF.columns(5)

	//We print the name of the seventh column.
	netflixDF.columns(6)

//4. SHOWING THE SCHEME
//We use the function *printSchema()* to see the structure of the data frame.

	//We print the schema.
	netflixDF.printSchema()

//5. PRINTING THE FIRST FIVE COLUMNS
//We print the first five columns of the data frame "netflixDF" with the *select()* function.

	//We print the first 5 columns of the data frame.
	netflixDF.select("Date","Open","High","Low","Close").show(5)

//6. USING DESCRIBE TO LEARN ABOUT THE DATA FRAME
//We use the *describe()* function to learn more details about the data frame. Each column presents the total of elements, the mean, the stddev, the min value and the max value.

	//Describing the columns of the data frame.
	netflixDF.describe().show()

//7. NEW COLUMN "HV RATIO"
//We create a new data frame with a brand new column called "HV Ratio". This new column have the results of the relation between the column "High" and the column "Volume" of the data frame netflixDF.
//As every relation, We decided to divide the column High between the column Volume. We keep the result of that relation in the value relationHV.

	//We obtain the result of the relation between High and Volume, and keep the result in the value relationHV.
	val relationHV = netflixDF("High")/netflixDF("Volume")

//We create a new data frame called newNetflixDF with the withColum() function to add a new column. We create this collumn as HV Ratio and We fill the blanks with the results contained in the relationHV value.

	//Then, We create a brand new data frame with a new column called HV Ratio with the previous results.
	val newNetflixDF = netflixDF.withColumn("HV Ratio", relationHV)

//We show the new data frame newNetflixDF at the end.

	//Finally, We show the new data frame.
	newNetflixDF.show()

//8. THE DAY WITH THE MAX VALUE IN THE COLUMN "CLOSE"
//In order to obtain the max value in the "Close" column, We decided use the select sentence, where We use the column "Date" to see the day, and the column "Close" to see the close value. We sort the data in descendent mode in order to see the greatest value first, then We show just the first row.

	//Comentario
	newNetflixDF.select(
		
		newNetflixDF("Date"),
		newNetflixDF("Close")
		
	).sort(desc("Close")).show(1)

//9. EXPLAIN WITH YOUR OWN WORDS THE MEANING OF THE "CLOSE" COLUMN

	//The close column represents the value with that Netflix ended the day.

//### 10. MAX VALUE AND MIN VALUE IN THE COLUMN "VOLUME"
//We use the *max()* and *min()* functions in the *newNetflixDF* data frame with the *agg()* function. We show the result at the end.

	newNetflixDF.agg(
	
		max("Volume"),
		min("Volume")
		
	).show()

//11. USING SPARK/SCALA SYNTAX TO SOLVE PROBLEMS
//First of all, We import the "spark.implicits._" in order to use the $-notation.

	// This import is needed to use the $-notation
	import spark.implicits._

//11a. AMOUNT OF DAYS WHEN "COUNT" WAS LOWER THAN 600
//We decided to filter the data frame "newNetflixDF" where the values in the "Close" column were lower than 600, and We count those values to print the result.

	//All the "Close" values lower than 600.
	newNetflixDF.filter($"Close"<600).count()

//11b. PERCENT OF TIME WHEN "HIGH" WERE HIGHER THAN 500
//To obtain the percent of time when the values in the column "High" were greater than 500, We decided to create three variables.
//The first one was the total amount of values in the column "High", representing the 100%.

	//Counting all the data in the column "High".
	var allData:Double = newNetflixDF.select($"High").count()

//The second one was the amount of values in the "High" column, where the values were greater than 500.

	//Counting all the "High" values above 500.
	var higher500:Double = newNetflixDF.filter($"High">500).count()

//The third and last one was the percent of values in the "High" column, where the values were above 500. In this one, We decided to obtain first the product between higher500 and 100, then we divide the result with the total of values in the data frame in general.

	//Percent of values in "High" above 500.
	var bresult:Double = (higher500*100)/allData

//11c. PEARSON CORRELATION BETWEEN "HIGH" AND "VOLUME"
//We use the *corr()* function in order to obtain the pearson correlation between the columns "High" and "Volume".

	newNetflixDF.select(
	
		corr("High", "Volume")
		
	).show()

//11d. MAX VALUE IN THE "HIGH" COLUMN BY YEAR
//We use the *groupBy()* function to group the data by the year. We show the "Date" column as "Year" and We show the "High" column with the max values. We sort by the Year when We show the results.

	newNetflixDF.groupBy(
		
		year($"Date").alias("Year")
		
	).agg(max($"High").alias("Max High")).sort($"Year").show()

//11e. AVERAGE OF THE "CLOSE" COLUMN BY EACH MONTH
//We use the *groupBy()* function to group the data by the month. We show the "Date" column as "Month" and We show the "Close" column with the average of the values. We sort by the Month when We show the results.

	newNetflixDF.groupBy(

		month($"Date").alias("Month")

	).agg(avg($"Close").alias("Average Close")).sort($"Month").show()