package com.example.basictestapp

fun main() {
    println("Hello RTU!")
    //let's rock this!

    //this is a comment

    //lets see how variables and values work in kotlin
    //val is used to declare a read-only variable
    val name = "RTU" //we should use val as default
    //also note name is type inferred to String
    //name = "Riga Technical University" //this will give an error
    println(name)
    //if we need to mutate a variable, we should use var
    var age = 100 //type inference to Int
    age = 150
    println("$name is over $age years old") //we are using string interpolation here
    age += 1
    println("$name is over $age years old") //we are using string interpolation here
    age = age + 50
    println("$name is over $age years old") //we are using string interpolation here
    val a = 10 / 3 // integer division
    val b = 10 / 5
    val c = 10 / 4.0 //so we are using a double here
    println("10 / 3 = $a")
    println("10 / 5 = $b")
    println("10 / 4.0 = $c")

    //full list of Kotlin Data types
    //https://kotlinlang.org/docs/reference/basic-types.html

    //we can also cast types
    //lets cast c to int
    val d = c.toInt()
    println("10 / 4.0 = $c but after converting to int = $d")
    //doubles still have 0.1 + 0.2 = 0.30000000000000004 problem just like all other languages based on IEEE 754 standard
    //https://0.30000000000000004.com/
    println("0.1 + 0.2 = ${0.1 + 0.2} but it should be 0.3")
    //note we used {} to do the math inside the string, in Kotlin we can perform operations inside strings, call on methods, and access properties


    //we can use method names to do math like times or add
    val e = 10.times(3)
    val f = 10.plus(3)
    println("10 * 3 = $e")
    println("10 + 3 = $f")
    //advantage of using method names is that we can use them as infix operators //we can chain them
    //lets use hex number
    val h = 0x2F //should be 47 in decimal because 2*16 + 15 = 32 + 15 = 47
    println("0x2F = $h")
    //now binary
    val binary = 0b1101 //should be 13 in decimal because 1*2^0 + 0*2^1 + 1*2^2 + 1*2^3 = 1 + 0 + 4 + 8 = 13
    println("0b1101 = $binary")
    //for big numbers we can use _ to separate them
    val bigNumber = 1_000_000
    println("1_000_000 = $bigNumber")

    //operations on Booleans
    val myTrue: Boolean = true //I do not need to type : Boolean, but I am doing it for clarity
    val myFalse: Boolean = false
    val boolNull: Boolean? = null //so if you need null as an option, you can use ? after the type

    println(myTrue || myFalse)
    println(myTrue && myFalse)
    println(!myTrue)
    println("boolNull is null? ${boolNull == null}")

    //for multi-line strings we can use triple quotes
    val bigString = """
        |This is a big string
        |We can put variables in it like $name
        |We can also do operations like 10 * 3 = ${10 * 3}
        |It can span multiple lines
        |We can use it for documentation
        |We can use it for SQL
        |We can use it for HTML
        |We can use it for JSON
        |We can use it for XML
        |We can use it for many things
    """.trimMargin()

    println(bigString)

    //string concatenation
    val concatString = "Hello " + "RTU"
    println(concatString)

    //let's look at basic control structures
    //if else

    val x = 10
//    val y = 20
    //lets make y random integer between 0 and 50
    val y = (0..50).random()
    //note range includes 0 and 50 as shown by Android Studio

    if (x > y) {
        println("x is greater than y, $x > $y")
    } else if (x < y) {
        println("x is less than y, $x < $y")
    } else {
        println("x is equal to y, $x = $y")
    }

    //we can use ranges to check if something is in a range
    if (y in 0..30) {
        println("y is between 0 and 30, $y")
    } else {
        println("y is not between 0 and 30, $y")
    }

    //when usage
    //when can be used as a replacement for switch in Java
    //or multiple if else if else statements
    when (y) {
        in 0..10 -> println("y is between 0 and 10, $y")
        in 11..20 -> println("y is between 11 and 20, $y")
        in 21..30 -> println("y is between 21 and 30, $y")
        else -> println("y is greater than 30, $y")
    }

    //now looping
    //for loop
    for (i in 0..10) {
        println("i = $i")
    }
    //we can use for loops to iterate over some collections
    val myPets  = listOf("dog", "cat", "fish", "bird")
    for (pet in myPets) {
        println("I have a $pet")
    }

    //if I need an index I can use withIndex
    //a bit similar to enumerate in Python
    //compare to the old c++ way of using an index
    for ((index, pet) in myPets.withIndex()) {
        println("I have a $pet at index $index")
    }
    //will index and pet available after the loop ends?
    //    println("I have a $pet at index $index") //no, they are not available
    //so they have block scope - tvērums latviešu valodā

    //step sizes in for loops
    for (i in 0..10 step 2) {
        println("i = $i")
    }

    //we can go down with optional step 3
    for (i in 10 downTo 0 step 3) {
        println("i = $i")
    }

    //I can use letter ranges
    for (c in 'd'..'k') { //notice the warning about shadowing c here.. this means c from outer scope remains in "shadow" until this scope ends
        println("c = $c")
    }
    //back to c from this scope
    //in general we should avoid shadowing variables

    //how about Unicode ranges?
    for (c in 'ā'..'ķ') {
//        println("c = $c, its unicode is ${c.toInt()}") //deprecated
        println("c = $c, its unicode is ${c.code}")
    }

    //while loop
    var z = 0
    while (z < 10) {
        println("z = $z")
        z++
    }

    //we also have do while loops
    //or as I call them "shoot first, ask questions later" loops
    var w = 5_000
    do {
        println("w = $w") //will run at least once
        w++
    } while (w < 10)
    println("w = $w") //will be 5001

    //finally we have repeat simply for repeating a block of code
    repeat(5) {
        println("Hello RTU!")
    }

    println("We are done for now! Congratulations!")
}