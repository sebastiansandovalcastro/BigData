# PRACTICE 5 - FIFTEEN DATA FRAME FUNCTIONS IN SCALA

<div align="center">

**TECNOLÓGICO NACIONAL DE MÉXIO**

INSTITUTO TECNOLÓGICO DE TIJUANA

SUBDIRECCIÓN ACADÉMICA
 
DEPARTAMENTO DE SISTEMAS Y COMPUTACIÓN
 
SEMESTRE SEPTIEMBRE 2020 – ENERO 2021

INGENIERÍA EN SISTEMAS COMPUTACIONALES

 
 [![](https://upload.wikimedia.org/wikipedia/commons/2/2e/ITT.jpg)](https://upload.wikimedia.org/wikipedia/commons/2/2e/ITT.jpg)

**DOCENTE**
JOSÉ CHRISTIAN ROMERO HERNÁNDEZ

**MATERIA**
DATOS MASIVOS
BDD-1704 SC9A, L - V 18:00 - 19:00 (91L4/0308)


**PRÁCTICA 5 - 15 FUNCIONES**


**ALUMNO**

PACHECO RAMÍREZ HUGO ANDRÉS	16210790

SANDOVAL CASTRO SEBASTIÁN	16212076


Tijuana, Baja California, al 19 de octubre de 2020
 
</div>

## Introducción

Esta práctica fue una familiarización con el funcionamiento de las funciones para data frames en scala.

## Desarrollo
 
Antes que nada, se realizan las importaciones de SparkSession y SparkImplicits para trabajar, así como la lectura del data frame a utilizar, en este caso, se hace uso del archivo csv CitiGroup2006_2008.
 
	//Importación y creacion de la variable SparkSession.
	import org.apache.spark.sql.SparkSession
	val spark = SparkSession.builder().getOrCreate()

	//Importación de SparkImplicits
	import spark.implicits._

	//DataFrame a utilizar
	val df = spark.read.csv("C:/Users/Sebas/Desktop/CitiGroup2006_2008")
 
### Función 1 - count()
 
Esta función sirve para contabilizar el número de elementos que conforman al data frame.
 
	//1. Función count()
	df.count() //Cuenta los elementos del data frame.
 
### Función 2 - first()
 
Esta función sirve para traer la primera fila del data frame.
 
	//2. Función first()
	df2 = df.first() //Muestra la primera fila del data frame.
	print(df2)

### Función 3 - filter()
 
Esta función sirve para filtrar según la condición proporcionada. En este ejemplo, solo se muestran los elementos de la columna c1 que sean exactamente igual a 490.
 
	//3. Función filter()
	df.filter($"_c1"===490).show() //Filtra según la condición.
 
### Función 4 - groupBy()
 
Esta función sirve para según la columna indicada en el data frame.
 
	//4. Función groupBy()
	df.groupBy($"_c1").count().show() //Agrupa según la columna.
 
### Función 5 - dtypes()
 
Esta función sirve para indicar el tipo de dato que presenta la columna seleccionada del data frame.
 
	//5. Función dtypes()
	df.dtypes(0) //Indica el tipo de dato de la columna.
 
### Función 6 - drop()
 
Esta función sirve para desechar la columna indicada del data frame.
 
	//6. Función drop()
	df.drop($"_c0").show() //Desecha la columna indicada.
 
### Función 7 - limit()
 
Esta función sirve para limitar a un número n de primeras filas que se muestran del data frame.
 
	//7. Función limit()
	df.limit(10).show() //Limita a “n” primeras filas.

### Función 8 - sort()
 
Esta función sirve para regresar un nuevo data frame ordenado según la columna especificada en orden ascendente.
 
	//8. Función sort()
	df.sort($"_c5").show() //Nuevo data frame ordenado según la columna especificada en orden ascendente.
 
### Función 9 - withColumnRenamed()
 
Esta función sirve para asignar un nuevo nombre a la columna proporcionada del data frame, renombrando con el segundo valor dado.
 
	//9. Función withColumnRenamed()
	df.withColumnRenamed("_c0", "Fecha").show() //Renombra una columna.
 
### Función 10 - select()
 
Esta función sirve para seleccionar los elementos de la columna dada del data frame.
 
	//10. Función select()
	df.select($"_c2").show() //Selección de los elementos en la columna indicada.
 
### Función 11 - repartition()
 
Esta función sirve para retornar un nuevo data frame particionado por lo indicado.
 
	//11. Función repartition()
	df.repartition(10, $"_c1").show(800) //Retorna un nuevo data frame particionado.
 

### Función 12 - dropDuplicate()
 
Esta función sirve para retornar columnas sin filas duplicadas del data frame.
 
	//12. Función dropDuplicates()
	df.dropDuplicates("_c0").show() //Regresa un nuevo data frame pero sin filas duplicadas de las columnas dadas.
 
### Función 13 - describe()
 
Esta función sirve para obtener estadísticas numéricas de la columna del data frame dada, como el total, la media, el mínimo, el máximo, etc.
 
	//13. Función describe()
	df.describe("_c1").show() //Regresa estadísticas numéricas de la columna como el total, la media, el mínimo, el máximo, etc.
 
### Función 14 - as()
 
Esta función sirve para regresar un data frame con un alias.
 
	//14. Función as()
	df.as("Apodo").show() //Regresa un dataset con un alias.
 
### Función 15 - sample()
 
Esta función sirve para regresar un data frame con una muestra aleatoria del original utilizando una semilla.
 
	//15. Función sample()
	df.sample(true, 2).show() //Regresa una muestra aleatoria del dataset original con una semilla.