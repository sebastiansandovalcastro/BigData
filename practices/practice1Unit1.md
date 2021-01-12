# PRACTICE 1 - PLAYING WITH SCALA FUNCTIONS

### 1. DEVELOP AN ALGORITHM IN SCALA THAT CALCULATES THE RADIUS OF A CIRCLE

    //The perimeter
    var P = 25.13

    //The Pi value
    val Pi = 3.1416

    //The radio of the circle (3.999554... in this example)
    var r = P / (2 * Pi)

### 2. DEVELOP AN ALGORITHM IN SCALA THAT TELLS ME IF A NUMBER IS A PRIME

    val numero = 3
    var cont = 0
    for(i <- Range(1, numero + 1)) {
    if( num % i == 0) {
    cont += 1
    }
    }
    if(cont != 2) {
    ("Numero no primo")
    } else {
    println("El numero es primo ")
    }

### 3. GIVEN THE VARIABLE bird = "tweet", USE THE INTERPOLATION OF STRINGS TO PRINT "I'm writing a tweet"

    //The variable bird
    var bird = "tweet"

    //The sentence with the variable
    var sentence = s"Estoy escribiendo un $bird"

### 4. GIVEN THE VARIABLE message = "Hello Luke, I am your father!", USE "SLICE" TO EXTRACT THE "Luke" SEQUENCE

    val star = "Hola Luke Yo soy tu padre"
    star.slice(5,9)

### 5. WHAT IS THE DIFFERENCE BETWEEN VALUE AND A VARIABLE IN SCALA?

    //Value is equal to 5
    val avalue = 5

    //Variable is equal to 5
    var avariable = 5

    //The value "avalue" can't change the value assigned at the beginning
    avalue = 10

    //The variable "avariable" change from 5 to 20
    avariable = 20

    //The difference is that the value can't change its value and the variable can.

### 6. GIVEN THE TUPLE (2,4,5,1,2,3,3,1416,23), RETURNS THE NUMBER 3.1416

    val tupla = ((2,4,5),(1,2,3),(3.1416,23))
    println(tupla._3)