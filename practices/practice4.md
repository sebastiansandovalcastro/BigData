# PRACTICE 4 - FIBONACCI

### 1. Fibonacci - Algorithm 1

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci1.png)

This is the basic fibonacci Algorithm in scala. Based on the descendant recursive version.

...

### 2. Fibonacci - Algorithm 2

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci2.png)

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

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci3.png)

This is the Fibonacci Algorithm iterative version.

...

### 4. Fibonacci - Algorithm 4

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci4.png)

This is the Fibonacci Algorithm iterative version with two variables.

...

### 5. Fibonacci - Algorithm 5

![ScreenShot](https://raw.github.com/sebastiansandovalcastro/BigData/images/unit1/practice4/fibonacci5.png)

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