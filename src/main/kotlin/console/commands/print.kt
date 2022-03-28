package console.commands

import service.DeckService

fun print(words: List<String>, deckService : DeckService) {

    if (words.size == 3 && words[1] == "deck" )
        deckService.printDeck(words[2])
    else if(words.size == 5 && words[1]=="discard" && words[2] == "from" && words[3] == "deck")
        deckService.printDiscard(words[4])
    else if (words.size == 6 && words[1]=="hand" && words[3] == "from" && words[4] == "deck" )
        deckService.printHand(words[5],words[2])
    else if (words.size == 4 && words[1]=="deck" && words[3] == "-all")
        deckService.printAllCards(words[2])
    else if (words[1]== "-allnames" && words.size == 2)
        deckService.printAllDeckNames()
    else
        println("Invalid Command")
}