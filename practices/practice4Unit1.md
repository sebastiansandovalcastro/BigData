# PRACTICE 4 - FIBONACCI

### 1. Fibonacci - Algorithm 1

<div align="center">

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci1.png)

</div>

This is the basic fibonacci Algorithm in scala. Based on the descendant recursive version.

In this version, We declare a variable called result at the beginning of the code. This variable is going to save the result value and it'll be returned when te function ends.

Then, We create a function with an "if" condition inside, where we indicate that if the "n" number is lower than 2, the result must be the  given "n" value.

If that is not the case, then the result must be the same function but with "n-1" as a parameter plus the same function but with "n-2" as a parameter.

In this case, We are doing the function recursive, calling it again inside the same function.

	//Fibonacci 1
	var result = 0

	def fibo1(n:Int):Int =
	{
		if(n < 2)
		{
			result =  n
		}
		else
		{
			result =  (fibo1(n - 1) + fibo1(n - 2))
		}

		return result
	}

	//The result must be 21
	fibo1(8)

### 2. Fibonacci - Algorithm 2

<div align="center">

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci2.png)

</div>

This is the Fibonacci Algorithm explicit formula version.

In this version We create three variables, the first one for the result and the other two for the formula result.

We declarate the variables as "double" type, and We define the function as a function that it's going to be given an "int" value, and after the process, it's going to give a "double" type value as result.

Inside the function We define an "if" condition that declares that if the "n" number is lower than 2, then we return the same value "n".

If the condition doesn't match, then we use the explicit function.

We make the "x" variable equal to the result of (1 + √5)/2, and then We make the "y" variable equal to the result of (xⁿ - (1 - x)ⁿ)/√5.

We save the "y" value in the "result" variable and We return that variable as result.

	//Fibonacci 2
	var result : Double = 0

	var x : Double = 0
	var y : Double = 0

	def fibo2(n:Int):Double =
	{
		if(n < 2)
		{
			result =  n
		}
		else
		{
			x = ((1 + Math.sqrt(5))/2)
			y = (((Math.pow(x, n)) - (Math.pow(1 - x, n)))/( Math.sqrt(5)))
			result = y
		}

		return result
	}

	//The result must be 21
	fibo2(8)

### 3. Fibonacci - Algorithm 3

<div align="center">

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci3.png)

</div>

This is the Fibonacci Algorithm iterative version.

In this version, We define three variables (a, b, c) at the beginning.

We give to "b" the value of 1 and we start a loop from 0 to "n", where "c" is going to be the sum of "b" and "a", then We give to "a" the "b" value, and We give to "b" the "c" value, till We reach the iteration number "n".

We return the "a" value at the end.

	//Fibonacci 3
	var a : Int = 0
	var b : Int = 0
	var c : Int = 0

	def fibo3(n:Int ):Int=
	{
		b = 1

		for (k <- Range(0,n))
		{
			c = b + a
			a = b
			b = c
		}

		return a
	}

	//The result must be 21
	fibo3(8)

### 4. Fibonacci - Algorithm 4

<div align="center">

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci4.png)

</div>

This is the Fibonacci Algorithm iterative version with two variables.

In this version, We define two variables (a, b) at the beginning.

We give to "b" the value of 1 and we start a loop from 0 to "n", where "b" is going to be the sum of "b" and "a", then We give to "a" the "b" minus "a" value till We reach the iteration number "n".

We return the "a" value at the end.

	//Fibonacci 4
	var a : Int = 0
	var b : Int = 0

	def fibo4(n:Int ):Int=
	{
		b = 1

		for (k <- Range(0,n))
		{
			b = b + a
			a = b - a
		}

		return a
	}

	//The result must be 21
	fibo4(8)

### 5. Fibonacci - Algorithm 5

<div align="center">

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci5.png)

</div>

This is the Fibonacci Algorithm iterative vector version.

In this version, We declare a variable called "result" and an vector called "vector" and We left them empty.

Inside the created function, We make an "if" sentence where, if the required "n" value is lower than 2, then we assign that same value to the "result" variable.

In the other hand, if the "n" value is equal or greater than 2, then We assign to the vector a range of numbers between 0 and (n+1).

We add a 0 in the vector with the position 0 and We add a 1 to the vector with the position 1.

Then, We start a loop where We assign to "k" a range between 2 and (n+1).

The vector in the "k" position is going to be equal to vector(k-1) +vector(k-2), until we reach the (n+1) iteration.

We assign that result to the "result" variable and we return that variable at the end.

	//Fibonacci 5
    var result = 0
    var vector = Array(0)

	def fibo5(n:Int ):Int=
	{
        if(n < 2)
        {
            result = n
        }
        else
        {
            vector = Array.range(0, (n + 1))
            vector(0) = 0
            vector(1) = 1

            for (k <- Range(2, (n + 1)))
            {
                vector(k) = vector(k - 1) + vector(k - 2)
                result = vector(k)
            }
        }

        return result
	}

	//The result must be 21
	fibo5(8)
