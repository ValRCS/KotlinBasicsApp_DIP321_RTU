package com.example.basictestapp

fun add(a: Int = 2, b: Int = 3): Int {
    //we could do more stuff here
    return a + b
}

fun tempToday(day: String, temp: Int){
    println("Today is $day and it's $temp degrees.")
}

fun reformat(str: String,
             divideByCamelHumps: Boolean,
             wordSeparator: Char,
             normalizeCase: Boolean = true): String {
    //we take incoming string and split it by camel humps and then join it with wordSeparator
    //if normalizeCase is true then we convert the string to lower case
    //if normalizeCase is false then we return the string as is
//    var result = "" no need for variables
    //our Platonic ideal is to have as few variables as possible
    val result = if (divideByCamelHumps)
        str.split("(?=[A-Z])".toRegex()).joinToString(separator = wordSeparator.toString())
    else //split by whitespace
        str.split(" ").joinToString(separator = wordSeparator.toString())


    if (normalizeCase) {
        return result.lowercase()
    }
    //could have used else but no need
    return result
}

fun double(x: Int): Int{
    return x * 2
}

fun triple(x: Int): Int = x * 3 //shorter way to write simple functions

fun encodeMsg(msg: String, encode: (String) -> String): String {
    //i could do more stuff here
    return encode(msg)
}

//lets make myEncoder function which takes string and does rot13 encoding
fun myEncoder(msg: String): String {
    //Docstring that only works with English characters
    //Rot13 is a simple letter substitution cipher that replaces a letter with the 13th letter after it in the alphabet
    return msg.map {
        when {
            it in 'a'..'z' -> {
                val shifted = it + 13
                if (shifted > 'z') ('a' + shifted.code - 'z' - 1).toChar() else shifted
            }
            it in 'A'..'Z' -> {
                val shifted = it + 13
                if (shifted > 'Z') ('A' + shifted.code - 'Z' - 1).toChar() else shifted
            }
            else -> it
        }
    }.joinToString("")
}

fun main() {
    println("Hello RTU!")
    //let's rock this!

    val a = 10
    val b = 20
    val c = add(a, b)
    println("Sum of $a and $b is $c")
    //i can pass values as named so switching if need be
    //generally we should not switch the order of parameters
    val d = add(b = 50, a = 70)
    println("Sum of 70 and 50 is $d")
    val defaultSum = add() //should be 5
    println("Sum of 2 and 3 is $defaultSum")
    //named arguments could be used to skip some parameters
    val defaultSum2 = add(b = 100) //should be 102
    println("Sum of 2 and 100 is $defaultSum2")

    tempToday("Friday", 4)

    //lets test our reformat function
    val inputStr = "Hello RTU Datoriki"
    val outputStr = reformat(inputStr, true, '-')
    println("Incoming string is $inputStr")
    println("Reformatted string is $outputStr")
    val outputStr2 = reformat(inputStr, false, '_')
    println("Reformatted string is $outputStr2")

    //lets test our double function
    val x = 10
    val x2 = double(x)
    println("Double of $x is $x2")
    val x3 = triple(x)
    println("Triple of $x is $x3")

    val dirtLevel = 20
    //now we introduce a lambda function
    //in this case we save the lambda function to a variable
    val waterFilter = {level: Int -> level / 2}
    println(waterFilter(dirtLevel))

    //we can also pass the lambda function directly
    val encodedResult = encodeMsg("Hello RTU!", {msg -> msg.reversed()}) //Android studio offers to move it outside
    println(encodedResult)
    //You can pass a lambda as a function parameter without putting it inside the parentheses.
    val encodedResult2 = encodeMsg("Hello RTU!") {msg -> msg.reversed()} //so syntax sugar we save one comma...
    println(encodedResult2)

    //we could pass in an existing function as well such as uppercase
    val upperResult = encodeMsg("Hello RTU!", String::uppercase)
    println(upperResult)

    //i could use my own function as a parameter as long as it has string as input and output
    val rot13Result = encodeMsg("Hello RTU!", ::myEncoder)
    println(rot13Result)
    val rot13Again = encodeMsg(rot13Result, ::myEncoder)
    println(rot13Again) //we should see Original Text again

    //lets create a list of 10 integers
    val numbers = (1..10).toList()
    //lets filter out even numbers by passing in lambda function
    val evenNumbers = numbers.filter { it % 2 == 0 } //sure beats writing a loop?
    println(evenNumbers)

    val instruments = listOf("viola", "cello", "violin")
    val eager = instruments.filter { it [0] == 'v' }
    println("eager: $eager")
    val lazy = instruments.asSequence().filter { it[0] == 'v' }
    println("lazy: $lazy")
    //we use lazy evaluation to avoid doing work until we need to
    //typically we would do it when we are using multiple filters and maps, multiple transformations
    //we only create a list at the end when we need it

    //so lets see what happens when we need it
    println("lazy: ${lazy.toList()}")

    //now for the finale let's lazily get a list of 100 numbers and get even squares using filter and map
    //eager
    val dividesBy7Squares = (1..100).filter { it % 7 == 0 }.map { it * it }
    //lazy - faster, here just a tiny bit but with more data it would be more noticeable
    val dividesBy7Squares2 = (1..100).asSequence().filter { it % 7 == 0 }.map {"$it squared = ${ it * it }" }.toList()

    println("dividesBy7Squares: $dividesBy7Squares")
    println("dividesBy7Squares2: $dividesBy7Squares2")

}

