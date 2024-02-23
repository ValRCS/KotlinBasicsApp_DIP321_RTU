package com.example.basictestapp

//lets make a class House with colors and number of windows and isForSale property
class House(val name: String = "RTU",
            var color: String = "white",
            private var windows: Int = 2,
            var isForSale: Boolean = false,
            secret: String = "hunter2" //use case for this would be to store a secret that is only available in the constructor
) {

//    var color: String = "white"
//    var windows: Int = 2
//    var isForSale: Boolean = false
    private var secretCopy = secret
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

    //by adding private only other methods in this class can access this method
    private fun checkSaleStatus(): Boolean{
        //we could do some checks here
        println("Checking Sale status")
        return isForSale
    }

    fun showSecretCopy(){
        println("The secret is $secretCopy")
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

interface Shape {
    fun computeArea() : Double
}

//lets make another interface that could be applicable to Circle
interface Coloring {
    val color: String
    fun paint(newColor: String) //notice no implementation just a contract!
}

class Circle(private val radius:Double) : Shape, Coloring {
    //I have to implement all the methods from Shape and Coloring and also the properties
    override fun computeArea() = Math.PI * radius * radius
    override var color: String = "white"
    override fun paint(newColor: String) {
        color = newColor
    }
}

//abstract class example
//such class can not be instantiated, we need to inherit from it
abstract class Food {
    abstract val kcal : Int
    abstract val name : String
    //lets also make abstract method
    abstract fun getCalories() : Unit //using Unit since print does not return anything
    //I can also have non-abstract methods and properties
    val caloriesPerJoule = 4
    fun consume() = println("I'm eating ${name}")
}
class Pizza() : Food() {
    //i have to implement all the abstract properties
    //using override keyword
    override val kcal = 600
    override val name = "Pizza"
    //i need to implement get calories
    override fun getCalories() = println("Kalories ${kcal * caloriesPerJoule}") //formula is wrong though....
}


//extension functions lets us add extra functionality to existing classes even those that we do not have access to
fun Int.isOdd(): Boolean { return this % 2 == 1 }
//Int is Kotlin's Int class which is a wrapper for Java's int actually

//data class example
data class Player(val name: String, var score: Int)

//enum class example
enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    //I can add methods to enum class
    //wrap around to the next day
    fun nextDay() : Day {
        return when(this) {
            MONDAY -> TUESDAY
            TUESDAY -> WEDNESDAY
            WEDNESDAY -> THURSDAY
            THURSDAY -> FRIDAY
            FRIDAY -> SATURDAY
            SATURDAY -> SUNDAY
            SUNDAY -> MONDAY
        }
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
//    println("The secret is ${homersHouse.secretCopy}")
    //information Hiding we only access secretCopy by using showSecretCopy method
    homersHouse.showSecretCopy()
    //original secret is gone
//    println("The secret is ${homersHouse.secret}")
    homersHouse.paint("pink")
    //i could have changed window count directly
//    homersHouse.windows = 5 //i made windows private so I need to use a method to change it
    homersHouse.addWindows(1)
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

    val c = Circle(3.0) //radius is private so I can not access it directly
    println(c.computeArea())
    println(c.color)
    c.paint("red") //we implemented the method from Coloring, we had no choice but had to implement it :)
    println(c.color)

    Pizza().consume()    // "I'm eating Pizza" //note I did not make an object instance of Pizza, I just called the method directly

    //lets test our extension function
    println("3 is odd: ${3.isOdd()}")
    println("4 is odd: ${4.isOdd()}")

    //lets test our data class
    val player1 = Player("Homer", 100)
    val player2 = Player("Bart", 100)
    println(player1)
    println(player2)
    println(player1 == player2) //false
    //let Bart increase his score
    player2.score += 10
    println(player2)

    //lets test our enum class
    val day = Day.MONDAY
    println(day)
    //again we use enum to limit the values that can be assigned to a variable
    var flexibleDay = Day.MONDAY
    flexibleDay = Day.FRIDAY
    //can we assign a string to it?
    //flexibleDay = "Friday" //nope
    //is there a way to increase the value of flexibleDay?
    //flexibleDay += 1 //nope
    //now we can use nextDay method
    println(flexibleDay.nextDay())
    //next next day
    println(flexibleDay.nextDay().nextDay())

    //lets test our Animal class //note it is in the same package so I do not need to import it
    val myAnimal = Animal("Dog", 3, true, "brown")
    myAnimal.prettyPrint()
}