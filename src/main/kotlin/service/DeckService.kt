package service

import data.Card
import data.Deck
import data.Hand
import data.enum.SuiteType

fun DeckService() = DeckService(SuiteType.CHARACTER) //factory method instead of secondary constructor
class DeckService(val type: SuiteType) {

    val decks :MutableMap<String, Deck> = mutableMapOf<String,Deck>() //in Kotlin, most objects are immutable by default,
                                                                    // so you have to specifically reference the mutable version

    fun addDeck(deckName : String) {
        if(ValidationService.isValidDeck(deckName,decks)) {
            decks[deckName] = Deck(deckName,type)
            // this is a kotlin string template, you can embed variables right into the string
            println("added new deck: $deckName")
        }
    }

    fun addHandIfNeeded(handName : String, deck : Deck) {
        if (!deck.hands.containsKey(handName)) {
            deck.hands.put(handName, Hand(mutableListOf<Card>(),handName))
        }
    }

    fun deleteDeck(deckName: String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            decks.remove(deckName)
            println("Successfully deleted deck: $deckName")
        }
    }

    fun deleteAll() {
        decks.clear()
        println("All decks have been deleted. Now you can start fresh!")
    }

    fun deleteDiscard(deckName: String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            val discard = decks[deckName]!!.discard
            for (card in discard) {
                decks[deckName]!!.cards.add(card)
            }
            discard.clear()
            print("The discard pile has been returned to the deck $deckName")
        }
    }

    fun deleteFromHand(deckName : String, handName : String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            val deck = decks[deckName]
            if (ValidationService.doesDeckContainHand(deck!!,handName)) {
                val hand = deck.hands[handName]
                for (card in hand!!.cards) {
                    deck.discard.add(card)
                }
                deck.hands.remove(handName)
                print("Hand $handName has been deleted from deck $deckName")
            }
        }
    }

    fun deleteAllHands(deckName : String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            val deck = decks[deckName]
            for(hand in deck!!.hands) {
                for (card in hand!!.value.cards) {
                    deck.discard.add(card)
                }
            }
            deck.hands.clear()
            print("All hands have been deleted from deck $deckName")
        }
    }

    fun shuffle(deckName : String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            val deck = decks[deckName]
            deck!!.shuffle()
            println("$deckName has been shuffled")
        }

    }

    fun shuffleAllCards(deckName : String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            deleteDiscard(deckName)
            val deck = decks[deckName]
            for(hand in deck!!.hands) {
                for (card in hand!!.value.cards) {
                    deck.cards.add(card)
                }
            }
            deck.hands.clear()
            deck!!.shuffle()
            println("All cards in $deckName has been shuffled")
        }
    }

    fun shuffleAllDecks() {
        for (deck in decks) {
            deck.value.shuffle()
        }
        println("All decks have been shuffled")
    }

    fun shuffleAllDecksAllCards() {
        for (deck in decks) {
            shuffleAllCards(deck.key)
        }
    }

    fun printDeck(deckName : String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            val cards = decks[deckName]!!.cards
            println("Cards: ")
            for (card in cards) {
                print("${card.rank}${card.suite} ")
            }
            println()
        }
    }

    fun printHand(deckName : String, handName : String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            val deck = decks[deckName]
            if (ValidationService.doesDeckContainHand(deck!!, handName)) {
                val hand = deck.hands[handName]
                println("Cards in Hand $handName: ")
                for (card in hand!!.cards) {
                    print("${card.rank}${card.suite} ")
                }
                println()
            }
        }
    }

    fun printDiscard(deckName: String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            val cards = decks[deckName]!!.discard
            println("Cards in Discard Pile: ")
            for (card in cards) {
                print("${card.rank}${card.suite} ")
            }
            println()
        }
    }

    fun printAllCards(deckName: String) {
        if (ValidationService.doesDeckExist(deckName, decks)) {
            printDeck(deckName)
            printDiscard(deckName)
            val deck = decks[deckName]
            for (hand in deck!!.hands) {
                printHand(deckName, hand.key)
            }
        }
    }

    fun printAllDeckNames() {
        println("Deck Names: ")
        println()
        for (deck in decks) {
            println(deck.key)
        }
    }

    fun drawToHand(numOfCardsString: String, deckName: String, handName: String){
        val numOfCards = numOfCardsString.toIntOrNull()
        if (ValidationService.doesDeckExist(deckName, decks)
            && ValidationService.isValidDraw(numOfCards,decks[deckName]!!)) {
            val deck = decks[deckName]!!
            addHandIfNeeded(handName,deck)
            val cards = deck.cards
            for (i in 0 until numOfCards!!) {
                deck.hands[handName]!!.cards.add(cards[0])
                deck.cards.remove(cards[0])
            }
        }
    }

    fun drawToDiscard(numOfCardsString: String, deckName: String) {
        val numOfCards = numOfCardsString.toIntOrNull()
        if (ValidationService.doesDeckExist(deckName, decks)
            && ValidationService.isValidDraw(numOfCards,decks[deckName]!!)) {
            val deck = decks[deckName]!!
            val cards = deck.cards
            for (i in 0 until numOfCards!!) {
                deck.discard.add(cards[0])
                deck.cards.remove(cards[0])
            }
        }
    }

}