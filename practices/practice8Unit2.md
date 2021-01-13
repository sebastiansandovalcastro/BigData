# PRACTICE 8 - Linear-Support-Vector-Machine (TEAM 6 - CODE ANALYSIS ) 
In this practice, we analyzed the team 6 code: Linear-Support-Vector-Machine_.

## DESCRIPTION
They are also known by the acronym SVM for its acronym in English (Support Vector Machines). They can be used for both regression and classification.

They are a set of supervised learning algorithms developed by Vladimir Vapnik and his team at AT&T Labs.

An SVM builds a hyperplane or set of hyperplanes in a very high (or even infinite) dimensional space that can be used in classification or regression problems. A good separation between the classes will allow a correct classification.

```scala
//importing the Libraries

import org.apache.spark.ml.classification.LinearSVC
//Load training data

val training = spark.read.format("libsvm").load("C:/Users/DELL/Desktop/LSVMExample/sample_libsvm_data.txt")

val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
//Fit the model

val lsvcModel = lsvc.fit(training)
//Print the coefficients and intercept for linear svc

println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
//:load svmexample.scala