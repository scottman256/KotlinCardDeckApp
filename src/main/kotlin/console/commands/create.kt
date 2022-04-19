package console.commands

import service.DeckService

// In Kotlin, you can write classless code. This allows me in this case, to organize these command line classes by method
// where each method represents a different command for the application.

// As these command classes are not classes, I put their names in lowercase

fun create(words: List<String>, deckService : DeckService) {
    // the if statement has a very similar syntax to java...
    if (words.size == 3 && words[1] == "deck" )
        deckService.addDeck(words[2]) //like java, you don't need {} if the if statement is one line.
    else
        println("Invalid Command")
}