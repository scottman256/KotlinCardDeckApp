package console.commands

import service.DeckService

fun create(words: List<String>, deckService : DeckService) {
    // the if statement has a very similar syntax to java...
    if (words.size == 3 && words[1] == "deck" )
        deckService.addDeck(words[2]) //like java, you don't need {} if the if statement is one line.
    else
        println("invalid command")
}