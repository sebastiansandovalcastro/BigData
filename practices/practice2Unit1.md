# PRACTICE 2 - LISTS, ARRAYS AND MAPS

### 1. CREATE A LIST CALLED "List" WITH THE ELEMENTS "Red", "White" AND "Black"

	/* We create the list "Lista" with the string values "Rojo", "Blanco" and "Negro". */
	var Lista = List("Rojo","Blanco","Negro")

### 2. ADD 5 MORE ITEMS TO "List": "Green", "Yellow", "Blue", "Orange" AND "Pearl"

	/* We add five more string values to the same list "Lista" with the ":::" between the list We already have and the new elements: "Verde", "Amarillo", "Azul", "Naranja" and "Perla". */
	Lista = Lista ::: List("Verde","Amarillo","Azul","Naranja","Perla")

### 3. BRING THE "List" ELEMENTS: "Green", "Yellow" AND "Blue"

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

### 4. CREATE AN ARRANGEMENT OF NUMBER IN RANGE OF 1 - 1000 IN STEPS OF 5 BY 5

	/* We create an Array called "Arreglo" and We fill it with numbers from 1 to 1000 but in 5 and 5 steps with "Array.range(n1,n2,nrange)" */
	val Arreglo = Array.range(1,1000,5)

### 5. WHAT ARE THE UNIQUE ITEMS IN THE "List (1,2,2,4,6,7,3,7)" LIST? (USE CONVERSION TO SETS)

	val mylist = List(1,3,3,4,5,7,3,7)
    mylist.toSet

### 6. CREATE A MOBILE MAP CALLED "Names" THAT CONTAINS THE FOLLOWING: "Jose", 20, "Luis", 24, "Ana", 23, "Susana", 27

	val nombres = collection.mutable.Map(("Jose",20),("Luis",24),("Ana",23),("Susana",27))
	nombres.keys

#### 6-A. PRINT ALL KEYS ON THE MAP

	println(nombres)

#### 6-B. ADD THE FOLLOWING VALUE TO THE MAP: "Miguel", 23

	nombres +=(("Miguel",23))