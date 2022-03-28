package console.commands

import service.DeckService

fun shuffle (words: List<String>, deckService : DeckService) {
    if (words.size == 3 && words[1] == "deck")
        deckService.shuffle(words[2]) //like java, you don't need {} if the if statement is one line.
    else if (words.size == 4 && words[1] == "deck" && words[3] == "-all")
        deckService.shuffleAllCards(words[2])
    else if (words.size == 2 && words[1] == "-all")
        deckService.shuffleAllDecks()
    else if (words.size == 3 && words[1] == "-all" && words[2] == "-all")
        deckService.shuffleAllDecksAllCards()
    else
        println("invalid command")
}