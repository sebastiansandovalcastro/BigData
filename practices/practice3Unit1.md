# PRACTICE 3 - ANALYZING THE CODE

## ANALYSIS 1
In the following code We will analyze what are the even and odd numbers of 2 lists.

The listEvens function will receive the value of a list, the for cycle will allow us to analyze each of the data in the list, while the if condition tells us that if the residue of the number divided by 2 is 0 the number will be even, otherwise it will be odd.

In the last part the lists with their respective values are declared and sent to the listEvens function.

	def listEvens(list:List[Int]): String ={ 
		for(n <- list)
		{
			if(n%2==0)
			{
				println(s"$n is even")
			}
			else
			{
				println(s"$n is odd")
			}
		}
		return "Done"
	}

	//We define the first and second lists of numbers in l and l2
	val l = List(1,2,3,4,5,6,7,8)
	val l2 = List(4,3,22,55,7,8)

	//We execute the listEvents function of l and l2
	listEvens(l) 
	listEvens(l2)

## ANALYSIS 2
The lucky 7 code is about a function that receives a list and through a cycle for.

We are presented with 2 conditions, if the number is equal to 7 it will return us a 14, while if the number is different from 7 We will return the value of that number.

	def afortunado(list:List[Int]): Int={
		var res=0
		
		for(n <- list)
		{
			//Condition if n is equal to 7
			if(n==7)
			{ 
				res = res + 14
			}
			else
			{
				res = res + n
			}
		}
		return res
	}

	//The list is created and the value of the function is printed
	val af= List(1,7,7)
	println(afortunado(af))

## ANALYSIS 3
Here We will see if there is a balance in the sum of the numbers in a list.

You define the balance function that will receive a list and the return value will be boolean, the first and second variables are declared and the value 0 is assigned, and the second variable is assigned the value of the sum of the values in the list.

A cycle starts because what you are going to do is assign the first variable its own value plus the list value in its "i" position, and the second variable is assigned its same value minus the list value in its "i" position. Then there is a condition that tells us that if the first is equal to second it will return the value true, as long as this does not pass the return value will be false.

	def balance(list:List[Int]): Boolean={
		var primera = 0
		var segunda = 0

		segunda = list.sum 

		for(i <- Range(0,list.length))
		{ 
			primera = primera + list(i)
			segunda = segunda - list(i)

			if(primera == segunda)
			{
				return true
			}
		}
		return false
	}

	//Create 3 different lists and send the values to the balance function
	val bl = List(3,2,1)
	val bl2 = List(2,3,3,2)
	val bl3 = List(10,30,90)

	balance(bl)
	balance(bl2)
	balance(bl3)

## ANALYSIS 4
In the following code We will analyze a function that allows us to know if a word is palindroma, remembering that a palindrome is a word that to the right and vice versa is the same. 

First We define the palindrome function that will receive a string and return a Boolean if the word is equal to the word the other way around.

	def palindromo(palabra:String):Boolean ={
		return (palabra == palabra.reverse)
	}
	
	//Define words val palabra ="OSO", val palabra2 = "ANNA", val palabra3 = "JUAN"
	val palabra = "OSO" 
	val palabra2 = "ANNA"
	val palabra3 = "JUAN"
	
	//Print the result of function println(palindromo(palabra)), println(palindromo(palabra2)), println(palindromo(palabra3))
	println(palindromo(palabra))
	println(palindromo(palabra2))
	println(palindromo(palabra3))
