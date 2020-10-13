# PRACTICE 2

### 1. CREA UNA LISTA LLAMADA "Lista" CON LOS ELEMENTOS "Rojo", "Blanco" Y "Negro"

	/* We create the list "Lista" with the string values "Rojo", "Blanco" and "Negro". */
	var Lista = List("Rojo","Blanco","Negro")

### 2. AÑADIR 5 ELEMENTOS MÁS A "Lista": "Verde", "Amarillo", "Azul", "Naranja" Y "Perla"

	/* We add five more string values to the same list "Lista" with the ":::" between the list We already have and the new elements: "Verde", "Amarillo", "Azul", "Naranja" and "Perla". */
	Lista = Lista ::: List("Verde","Amarillo","Azul","Naranja","Perla")

### 3. TRAER LOS ELEMENTOS DE "Lista": "Verde", "Amarillo" Y "Azul"

	/* We call the "Verde", "Amarillo" and "Azul" elements from the list "Lista" using Lista.lift(position). */
	
	//"Verde"
	Lista.lift(3)
	
	//"Amarillo"
	Lista.lift(4)
	
	//Azul
	Lista.lift(5)
	
	/* Once We are sure about the position of the elements, We can just print them together with print(Lista(position)) */
	
	//(Verde, Amarillo, Azul)
	print(Lista(3), Lista(4), Lista(5))

### 4. CREAR UN ARREGLO DE NÚMERO EN RANGO DEL 1 - 1000 EN PASOS DE 5 EN 5

	/* We create an Array called "Arreglo" and We fill it with numbers from 1 to 1000 but in 5 and 5 steps with "Array.range(n1,n2,nrange)" */
	val Arreglo = Array.range(1,1000,5)

### 5. ¿CUÁLES SON LOS ELEMENTOS ÚNICOS DE LA LISTA "Lista(1,2,2,4,6,7,3,7)"? (UTILICE CONVERSIÓN A CONJUNTOS)

	val mylist = List(1,3,3,4,5,7,3,7)
    mylist.toSet

### 6. CREA UN MAPA MUTABLE LLAMADO "Nombres" QUE CONTENGA LO SIGUIENTE: "Jose", 20, "Luis", 24, "Ana", 23, "Susana", 27

  val nombres = collection.mutable.Map(("Jose",20),("Luis",24),("Ana",23),("Susana",27))
    nombres.keys

#### 6-A. IMPRIME TODAS LAS LLAVES DEL MAPA

	println(nombres)

#### 6-B. AGREGA EL SIGUIENTE VALOR AL MAPA: "Miguel", 23

	nombres +=(("Miguel",23))