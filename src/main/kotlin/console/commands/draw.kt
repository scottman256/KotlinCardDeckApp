package console.commands

import service.DeckService

fun draw (words: List<String>, deckService : DeckService) {

    if (words.size == 9 && words[2] == "cards" && words[3] == "from" && words[4] == "deck" && words[6] == "to" && words[7] == "hand" )
        deckService.drawToHand(words[1],words[5],words[8]) //like java, you don't need {} if the if statement is one line.
    else if (words.size == 6 && words[2] == "cards" && words[3] == "from" && words[4] == "deck" )
        deckService.drawToDiscard(words[1],words[5])
    else
        println("Invalid Command")
}