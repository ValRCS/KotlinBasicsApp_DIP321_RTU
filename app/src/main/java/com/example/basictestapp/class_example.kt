package com.example.basictestapp

//lets make a class House with colors and number of windows and isForSale property
class House(val name: String = "RTU",
            var color: String = "white",
            var windows: Int = 2,
            var isForSale: Boolean = false,
            secret: String = "hunter2" //use case for this would be to store a secret that is only available in the constructor
) {

//    var color: String = "white"
//    var windows: Int = 2
//    var isForSale: Boolean = false
    var secretCopy = secret
    //so let's make primary constructor block which just prints out the name of the house
    init {
        println("We just started constructing a house")
        println("The name of the house is $name") //so we can use the name property here already
        //secret is available only in this block of constructor
        println("The secret is $secret")
        //i could save the secret somewhere here in different variable
        prettyPrint() //we can start using the method we defined below
    }

    fun prettyPrint(){
        println("My house is named $name it is $color and has $windows windows and is for sale: $isForSale")
    }

    //lets make a method to paint the house
    fun paint(newColor: String){
        color = newColor
    }
    //lets make a method to add windows
    fun addWindows(numWindows: Int){
        windows += numWindows
    }
    //lets make a method to put the house on sale
    fun putOnSale(){
        isForSale = true
    }
    //lets make a method to take the house off sale
    fun takeOffSale(){
        isForSale = false
    }


    //lets create a closing init block - here this would be second init block
    //this block will be called after the primary constructor block
    init {
        println("We just finished constructing a house")
    }

}

//lets make a Person
class Person(var firstName: String, var lastName:String) {
    //custom getter for fullName
    var fullName:String = ""
        get() {
            //we could do some validation here
            return "$firstName $lastName"
        }
        set(value) {
            val components = value.split(" ")
            //again we could do some validation here like check if there are two components
            //like check if the components are valid names and so on
            firstName = components[0]
            lastName = components[1]
            field = value //so in custom setter field refers to the property itself here that would be fullName
        }

}

fun main() {
    //lets make a new house
    val myHouse = House() //so using class blueprint we created an object instance
//    println("My house is named ${myHouse.name} it is ${myHouse.color} and has ${myHouse.windows} windows and is for sale: ${myHouse.isForSale}")
//    myHouse.prettyPrint()
    //lets make a house for Homer
    //note I mixed the order of the parameters but that is okay since I named them
    val homersHouse = House("Homers House", windows = 4, color = "yellow") //so using class blueprint we created an object instance
//    homersHouse.prettyPrint()
    //I can print secretCopy but I can not print secret
    println("The secret is ${homersHouse.secretCopy}")
    //original secret is gone
//    println("The secret is ${homersHouse.secret}")
    homersHouse.paint("pink")
    //i could have changed window count directly
    homersHouse.windows = 5
    homersHouse.prettyPrint()

    //lets make a person Homer Simpson
    val homer = Person("Homer", "Simpson")
    println("Homer's full name is ${homer.fullName}")
    //lets change Homer to Crusty the Clown
    homer.firstName = "Crusty"
    //now we see the advantage of using custom getter
    println("Homer's full name is ${homer.fullName}")
    //now I could set the fullName and the name and lastName would be updated automatically
    homer.fullName = "Moe Szyslak"
    println("homer firstName is ${homer.firstName} and lastName is ${homer.lastName} and fullName is ${homer.fullName}")
}