# PRACTICE 1

### 1. DESARROLLAR UN ALGORITMO EN SCALA QUE CALCULE EL RADIO DE UN CÍRCULO

    //The perimeter
    var P = 25.13

    //The Pi value
    val Pi = 3.1416

    //The radio of the circle (3.999554... in this example)
    var r = P / (2 * Pi)

### 2. DESARROLLAR UN ALGORITMO EN SCALA QUE ME DIGA SI UN NÚMERO ES PRIMO

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

### 3. DADA LA VARIABLE bird = "tweet", UTILIZA LA INTERPOLACIÓN DE STRINGS PARA IMPRIMIR "Estoy escribiendo un tweet"

    //The variable bird
    var bird = "tweet"

    //The sentence with the variable
    var sentence = s"Estoy escribiendo un $bird"

### 4. DADA LA VARIABLE mensaje = "Hola Luke, ¡yo soy tu padre!", UTILIZA "SLICE" PARA EXTRAER LA SECUENCIA "Luke"

    val star = "Hola Luke Yo soy tu padre"
    star.slice(5,9)

### 5. ¿CUÁL ES LA DIFERENCIA ENTRE VALUE Y UNA VARIABLE EN SCALA?

    //Value is equal to 5
    val avalue = 5

    //Variable is equal to 5
    var avariable = 5

    //The value "avalue" can't change the value assigned at the beginning
    avalue = 10

    //The variable "avariable" change from 5 to 20
    avariable = 20

    //The difference is that the value can't change its value and the variable can.

### 6. DADA LA TUPLA (2,4,5,1,2,3,3.1416,23), REGRESA EL NÚMERO 3.1416

    val tupla = ((2,4,5),(1,2,3),(3.1416,23))
    println(tupla._3)