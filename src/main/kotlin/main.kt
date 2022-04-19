import console.commands.create
import console.commands.delete
import console.commands.draw
import console.commands.help
import console.commands.shuffle
import console.commands.print
import data.enum.SuiteType
import service.DeckService

// This is the main function (use fun to declare functions in kotlin), where the console application will begin execution.
// The function takes arguments, although for this program no arguments are used.
// This is an example of "classless code" in Kotlin, as the functions here do not belong to a class.

fun main() {
    println("Welcome to the Kotlin Deck Demo Application!\n")
    val type = getSuiteType()
    val deckService = DeckService(type)
 while (true) {
     println("Please enter a command:")
     var command = readLine()
     val words = command?.split(" ") ?: continue // null safety

     // when is like the java switch statement on steroids
     // it is more concise but also supports

     when(words[0]) {
        "create","add" -> create(words,deckService) // very clean to call methods from other files
        "delete","remove" -> delete(words,deckService)
        "shuffle" -> shuffle(words,deckService)
        "draw" -> draw(words,deckService)
        "print" -> print(words,deckService)
        "help" -> help()
        "exit", "end", "quit" -> break
        else -> println("You have entered an invalid command")
    }
     println(" ")
 }
    println("Bye Bye!")

}

// the return type of a method is designated with a colon (:)
// if no return type is specified, the return type is Unit
// Similar to Java Void. But it can be used for comparisons...  x = voidMethod() if (x is Unit)

fun getSuiteType() : SuiteType {

    // This is a multiline string literal, it makes it easy to embed multiple line strings in your code.
   println("""
                Please select how you would like the suite to be displayed, character or symbol
                
                1 - Character (H for Hearts, S for Space, C for Club, D for Diamond)
                2 - Symbol (uses unicode, may not display properly on some consoles, like windows command line)
       
                Enter your selection: 
       """.trimIndent())
        var choice = readLine()?.trim()

    when(choice) {
        "1" -> return SuiteType.CHARACTER
        "2" -> return SuiteType.SYMBOL
        else -> {println("Invalid selection. Please try again"); return getSuiteType()} // use ; for expressions on same line
    }
}
