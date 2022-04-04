package service

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.System.setOut
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse

class DeckServiceTest {

    val outContent: ByteArrayOutputStream = ByteArrayOutputStream()
    val out : PrintStream = System.out

    @BeforeTest
    fun setUpStream() {
        setOut(PrintStream(outContent))
    }

    @AfterTest
    fun restoreSteam() {
        setOut(out)
    }


    @Test
    fun testAddDeck(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        assert(deckService.decks.count() == 1)
    }

    @Test
    fun testDeleteDeck(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.deleteDeck("deck")
        assert(deckService.decks.count() == 0)
    }

    @Test
    fun testDeleteAll(){
        val deckService = DeckService()
        deckService.addDeck("deck1")
        deckService.addDeck("deck2")
        deckService.deleteAll()
        assert(deckService.decks.count() == 0)
    }

    @Test
    fun testDeleteDiscard(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.drawToDiscard("5","deck")
        deckService.deleteDiscard("deck")
        assert(deckService.decks["deck"]!!.discard.count() == 0)
    }

    @Test
    fun testDeleteFromHand(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.drawToHand("5","deck","hand")
        deckService.deleteFromHand("deck","hand")
        assertFalse(deckService.decks["deck"]!!.hands.contains("hand"))
    }

    @Test
    fun testDeleteAllHands(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.drawToHand("5","deck","hand1")
        deckService.drawToHand("5","deck","hand2")
        deckService.deleteAllHands("deck")
        assert(deckService.decks["deck"]!!.hands.isEmpty())
    }

    @Test
    fun testShuffle(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        val beforeShuffle = deckService.decks["deck"]!!.toString()
        deckService.shuffle("deck")
        val afterShuffle = deckService.decks["deck"]!!.toString()
        assert(beforeShuffle != afterShuffle)
    }

    @Test
    fun testShuffleAllCards(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.drawToDiscard("5","deck")
        deckService.shuffleAllCards("deck")
        assert(deckService.decks["deck"]!!.cards.count() == 52)
    }

    @Test
    fun testShuffleAllDecks() {
        val deckService = DeckService()
        deckService.addDeck("deck1")
        deckService.addDeck("deck2")
        val beforeShuffle1 = deckService.decks["deck1"]!!.toString()
        val beforeShuffle2 = deckService.decks["deck2"]!!.toString()
        deckService.shuffleAllDecks()
        val afterShuffle1 = deckService.decks["deck1"]!!.toString()
        val afterShuffle2 = deckService.decks["deck2"]!!.toString()
        assert((beforeShuffle1 != afterShuffle1) && (beforeShuffle2 != afterShuffle2))
    }

    @Test
    fun testShuffleAllDecksAllCards() {
        val deckService = DeckService()
        deckService.addDeck("deck1")
        deckService.drawToDiscard("10","deck1")
        deckService.addDeck("deck2")
        deckService.drawToHand("5","deck2","hand")
        deckService.shuffleAllDecksAllCards()
        assert(deckService.decks["deck1"]!!.cards.count() == 52 && deckService.decks["deck2"]!!.cards.count() == 52)
    }

    @Test()
    fun testPrintDeck(){
        val deckService = DeckService()
        deckService.addDeck("deck1")
        outContent.reset()
        deckService.printDeck("deck1")
        assert(outContent.toString().contains("Cards:"))
    }

    @Test()
    fun testPrintHand(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.drawToHand("5","deck","hand")
        outContent.reset()
        deckService.printHand("deck","hand")
        assert(outContent.toString().contains("Cards in Hand hand:"))
    }

    @Test()
    fun testPrintDiscard(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.drawToDiscard("5","deck")
        outContent.reset()
        deckService.printDiscard("deck")
        assert(outContent.toString().contains("Cards in Discard Pile:"))
    }

    @Test()
    fun testPrintAllCards(){
        val deckService = DeckService()
        deckService.addDeck("deck")
        deckService.drawToHand("5","deck","hand")
        deckService.drawToDiscard("5","deck")
        outContent.reset()
        deckService.printAllCards("deck")
        assert(outContent.toString().contains("Cards in Discard Pile:")
                && outContent.toString().contains("Cards in Hand hand:")
                && outContent.toString().contains("Cards:"))
    }

    @Test()
    fun testPrintAllDeckNames(){
        val deckService = DeckService()
        deckService.addDeck("deck1")
        deckService.addDeck("deck2")
        outContent.reset()
        deckService.printAllDeckNames()
        assert(outContent.toString().contains("deck1") && outContent.toString().contains("deck2"))
    }

}