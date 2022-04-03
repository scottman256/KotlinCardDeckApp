package console.commands

import service.DeckService

fun delete(words: List<String>, deckService: DeckService) {


    if(words.size == 2 && words[1] == "-all" )
        deckService.deleteAll()
    else if(words.size == 3 && words[1] == "deck" )
      deckService.deleteDeck(words[2])
    else if(words.size == 5 && words[1]=="discard" && words[2] == "from" && words[3] == "deck" )
        deckService.deleteDiscard(words[4])
    else if (words.size == 6 && words[1]=="hand" && words[2] == "-all" && words[3] == "from" && words[4] == "deck" )
        deckService.deleteAllHands(words[5])
    else if (words.size == 6 && words[1]=="hand" && words[3] == "from" && words[4] == "deck" )
        deckService.deleteFromHand(words[5],words[2])
    else
        println("Invalid Command")
}