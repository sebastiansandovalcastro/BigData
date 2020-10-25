# EVALUATION UNIT 1

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


**EVALUATION UNIT 1**


**STUDENT**

PACHECO RAMÍREZ HUGO ANDRÉS	16210790

SANDOVAL CASTRO SEBASTIÁN	16212076


Tijuana, Baja California, october 28, 2020.
 
</div>

## DEVELOPMENT

### 1. STARTING A SPARK SESSION

...

### 2. LOADING A FILE AND INFERRING THE DATA TYPES

...

### 3. SHOWING COLUMNS NAMES

...

### 4. SHOWING THE SCHEME

...

### 5. PRINTING THE FIRST FIVE COLUMNS

...

### 6. USING DESCRIBE TO LEARN ABOUT THE DATA FRAME

...

### 7. NEW COLUMN "HV RATIO"

...

### 8. THE DAY WITH THE MAX VALUE IN THE COLUMN "CLOSE"

...

### 9. EXPLAIN WITH YOUR OWN WORDS THE MEANING OF THE "CLOSE" COLUMN

...

### 10. MAX VALUE AND MIN VALUE IN THE COLUMN "VOLUME"

We use the *max()* and *min()* functions in the *newNetflixDF* data frame with the *agg()* function. Whe show the result at the end.

	newNetflixDF.agg(
	
		max("Volume"),
		min("Volume")
		
	).show()

<div align="center">

![10.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/evaluation/10.png)

</div>

### 11. USING SPARK/SCALA SYNTAX TO SOLVE PROBLEMS

First of all, We import the "spark.implicits._" in order to use the $-notation.

	// This import is needed to use the $-notation
	import spark.implicits._

#### 11a. AMOUNT OF DAYS WHEN "COUNT" WAS LOWER THAN 600

We decided to filter the data frame "newNetflixDF" where the values in the "Close" column were lower than 600, and We count those values to print the result.

	//All the "Close" values lower than 600.
	newNetflixDF.filter($"Close"<600).count()

<div align="center">

![11a.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/evaluation/11a.png)

</div>

#### 11b. PERCENT OF TIME WHEN "HIGH" WERE HIGHER THAN 500

To obtain the percent of time when the values in the column "High" were greater than 500, We decided to create three variables.

The first one was the total amount of values in the column "High", representing the 100%.

	//Counting all the data in the column "High".
	var allData:Double = newNetflixDF.select($"High").count()

The second one was the amount of values in the "High" column, where the values were greater than 500.

	//Counting all the "High" values above 500.
	var higher500:Double = newNetflixDF.filter($"High">500).count()

The third and last one was the percent of values in the "High" column, where the values were above 500. In this one, We decided to obtain first the product between higher500 and 100, then we divide the result with the total of values in the data frame in general.

	//Percent of values in "High" above 500.
	var bresult:Double = (higher500*100)/allData

<div align="center">

![11b.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/evaluation/11b.png)

</div>

#### 11c. PEARSON CORRELATION BETWEEN "HIGH" AND "VOLUME"

We use the *corr()* function in order to obtain the pearson correlation between the columns "High" and "Volume".

	newNetflixDF.select(
	
		corr("High", "Volume")
		
	).show()

<div align="center">

![11c.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/evaluation/11c.png)

</div>

#### 11d. MAX VALUE IN THE "HIGH" COLUMN BY YEAR

We use the *groupBy()* function to group the data by the year. We show the "Date" column as "Year" and We show the "High" column with the max values. We sort by the Year when We show the results.

	newNetflixDF.groupBy(
		
		year($"Date").alias("Year")
		
	).agg(max($"High").alias("Max High")).sort($"Year").show()

<div align="center">

![11d.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/evaluation/11d.png)

</div>

#### 11e. AVERAGE OF THE "CLOSE" COLUMN BY EACH MONTH

We use the *groupBy()* function to group the data by the month. We show the "Date" column as "Month" and We show the "Close" column with the average of the values. We sort by the Month when We show the results.

	newNetflixDF.groupBy(

		month($"Date").alias("Month")

	).agg(avg($"Close").alias("Average Close")).sort($"Month").show()

<div align="center">

![11e.png](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/evaluation/11e.png)

</div>