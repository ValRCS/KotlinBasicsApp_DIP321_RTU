package com.example.basictestapp

class Animal(val name: String, var age: Int=2, var isForSale: Boolean = false, var color: String = "white") {

    fun prettyPrint(){
        println("My animal is named $name it is $color and is $age years old and is for sale: $isForSale")
    }



    init {
        println("We just started constructing an animal")
        prettyPrint()
    }

    init {
        println("We just finished constructing an animal")
    }
}