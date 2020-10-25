//EVALUATION UNIT 1

//DEVELOPMENT

//1. STARTING A SPARK SESSION
//...

//2. LOADING A FILE AND INFERRING THE DATA TYPES
//...

//3. SHOWING COLUMNS NAMES
//...

//4. SHOWING THE SCHEME
//...

//5. PRINTING THE FIRST FIVE COLUMNS
//...

//6. USING DESCRIBE TO LEARN ABOUT THE DATA FRAME
//...

//7. NEW COLUMN "HV RATIO"
//...

//8. THE DAY WITH THE MAX VALUE IN THE COLUMN "CLOSE"
//...

//9. EXPLAIN WITH YOUR OWN WORDS THE MEANING OF THE "CLOSE" COLUMN
//...

//### 10. MAX VALUE AND MIN VALUE IN THE COLUMN "VOLUME"
//We use the *max()* and *min()* functions in the *newNetflixDF* data frame with the *agg()* function. Whe show the result at the end.

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