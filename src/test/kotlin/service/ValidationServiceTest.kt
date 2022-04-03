package service

import data.Card
import data.Deck
import data.Hand
import kotlin.test.Test
import kotlin.test.assertFalse

class ValidationServiceTest {

    @Test
    fun testIsValdLenngth(){
        assert(ValidationService.isValidLength("valid"))
    }

    @Test
    fun testIsValdLenngth_invalid(){
        assertFalse(ValidationService.isValidLength("invalidname!"))
    }

    @Test
    fun testDoesDeckNotExist() {
        val decks :MutableMap<String, Deck> = mutableMapOf<String,Deck>()
        decks.put("deck1",Deck("deck1"))
        decks.put("deck2",Deck("deck2"))
        assert(ValidationService.doesDeckNotExist("deck3",decks))
    }

    @Test
    fun testDoesDeckNotExist_invalid() {
        val decks :MutableMap<String, Deck> = mutableMapOf<String,Deck>()
        decks.put("deck1",Deck("deck1"))
        decks.put("deck2",Deck("deck2"))
        assertFalse(ValidationService.doesDeckNotExist("deck2",decks))
    }

    @Test
    fun testDoesDeckExist() {
        val decks :MutableMap<String, Deck> = mutableMapOf<String,Deck>()
        decks.put("deck1",Deck("deck1"))
        decks.put("deck2",Deck("deck2"))
        assert(ValidationService.doesDeckExist("deck2",decks))
    }

    @Test
    fun testDoesDeckExist_invalid() {
        val decks :MutableMap<String, Deck> = mutableMapOf<String,Deck>()
        decks.put("deck1",Deck("deck1"))
        decks.put("deck2",Deck("deck2"))
        assertFalse(ValidationService.doesDeckExist("deck3",decks))
    }

    @Test
    fun testCanAddDeck() {
        val decks :MutableMap<String, Deck> = mutableMapOf<String,Deck>()
        decks.put("deck1",Deck("deck1"))
        decks.put("deck2",Deck("deck2"))
        assert(ValidationService.canAddDeck(decks))
    }

    @Test
    fun testCanAddDeck_invalid() {
        val decks :MutableMap<String, Deck> = mutableMapOf<String,Deck>(
            "deck1" to Deck("deck1"), "deck2" to Deck("deck2"), "deck3" to Deck("deck3"), "deck4" to Deck("deck4"),
            "deck5" to Deck("deck5"), "deck6" to Deck("deck6"), "deck7" to Deck("deck7"), "deck8" to Deck("deck8"),
            "deck9" to Deck("deck9"), "deck10" to Deck("deck10")
        )
        assertFalse(ValidationService.canAddDeck(decks))
    }

    @Test
    fun testDoesDeckContainHand() {
        val deck = Deck("deck")
        val list = mutableListOf<Card>(Card("10",'♥'), Card("A",'♦'))
        deck.hands.put("hand", Hand(list,"hand"))
        assert(ValidationService.doesDeckContainHand(deck,"hand"))
    }

    @Test
    fun testDoesDeckContainHand_invalid() {
        val deck = Deck("deck")
        val list = mutableListOf<Card>(Card("10",'♥'), Card("A",'♦'))
        deck.hands.put("hand1", Hand(list,"hand"))
        assertFalse(ValidationService.doesDeckContainHand(deck,"hand2"))
    }

    @Test
    fun testIsValidDraw() {
        assert(ValidationService.isValidDraw(7,Deck("deck")))
    }

    @Test
    fun isValidDrawInvalid_invalid() {
        assertFalse(ValidationService.isValidDraw(53,Deck("deck")))
    }
}