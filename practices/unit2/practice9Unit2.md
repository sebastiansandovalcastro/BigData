# PRACTICE 9 - One vs Rest (TEAM 7 - CODE ANALYSIS ) 
In this practice, we analyzed the team 7 code: One vs Rest_.

## DEVELOPMENT

In the one-vs-all classification, for the data set of instances of class N, we have to generate the binary classifier models N. The number of class labels present in the data set and the number of generated binary classifiers must be the same.

```scala
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Cargar archivo de datos.
var inputData = spark.read.format("libsvm").load("/opt/spark/data/mllib/sample_multiclass_classification_data.txt")

// Generar la división de tren / prueba.
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

// Instanciar el clasificador base
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

// Crea una instancia del clasificador One Vs Rest.
val ovr = new OneVsRest().setClassifier(classifier)

// Entrena el modelo multiclase.
val ovrModel = ovr.fit(train)

// Puntuar el modelo en los datos de prueba.
val predictions = ovrModel.transform(test)

// Obtener evaluador.
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Calcula el error de clasificación en los datos de prueba.
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1 - accuracy}")