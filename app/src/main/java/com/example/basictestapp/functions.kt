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
}

