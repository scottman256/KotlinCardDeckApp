package service

import data.Deck

// This class will contain static Kotlin methods, using a companion object.

class ValidationService {
    companion object { // You can create static methods in Kotlin by using a companion object.
        fun isValidLength(name: String): Boolean {  // the return type is specified after the method signature
            if (name.length > 10) {
                println("The name of the deck you wish to add must have 10 or fewer characters");
                return false;
            }
            return true;
        }

        fun doesDeckNotExist(name: String, decks: MutableMap<String, Deck>): Boolean {
            if (decks.containsKey(name)) {
                println("There is alreay a deck with name $name")  // this is a string template, it lets you embed expressions in strings without concatenation.
                return false;
            }
            return true;
        }

        fun doesDeckExist(name: String, decks: MutableMap<String, Deck>): Boolean {
            if (!decks.containsKey(name)) {
                println("There is not a deck with name $name")  // this is a string template, it lets you embed expressions in strings without concatenation.
                return false;
            }
            return true;
        }

        fun canAddDeck(decks: MutableMap<String, Deck>): Boolean {
            if (decks.size >= 10) {
                println("You already have 10 decks of cards, so a new deck cannot be created")
                return false;
            }
            return true;
        }

        fun isValidDeck(name: String, decks: MutableMap<String, Deck>): Boolean {
            return isValidLength(name) && doesDeckNotExist(name, decks) && canAddDeck(decks);
        }

        fun doesDeckContainHand(deck: Deck, name: String) : Boolean {
            if (!deck.hands.containsKey(name)) {
                println("Cannot delete $name from deck ${deck.name}, because there is no hand with this name") //in string templates need to use {} for object properties
                return false;
            }
            return true;
        }

        fun isValidDraw(numOfCards: Int?, deck: Deck) : Boolean {
            if (numOfCards == null  || numOfCards > deck.cards.count()) {
                println("Invalid number of cards to draw")
                return false
            }
            return true;
        }
    }

}