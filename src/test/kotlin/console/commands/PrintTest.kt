package console.commands

import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import service.DeckService
import kotlin.test.Test

class PrintTest {

    @Test
    fun testPrint_deck() {
        val words = listOf("print", "deck", "testDeck")
        val ds : DeckService = mock()
        print(words, ds)
        verify(ds, times(1)).printDeck("testDeck")
    }

    @Test
    fun testPrint_discard() {
        val words = listOf("print", "discard", "from", "deck", "testDeck")
        val ds : DeckService = mock()
        print(words, ds)
        verify(ds, times(1)).printDiscard("testDeck")
    }

    @Test
    fun testPrint_hand() {
        val words = listOf("print", "hand", "testHand", "from", "deck", "testDeck")
        val ds : DeckService = mock()
        print(words, ds)
        verify(ds, times(1)).printHand("testDeck","testHand")
    }

    @Test
    fun testPrint_allDecks() {
        val words = listOf("print", "deck", "testDeck","-all")
        val ds : DeckService = mock()
        print(words, ds)
        verify(ds, times(1)).printAllCards("testDeck")
    }

    @Test
    fun testPrint_allNames() {
        val words = listOf("print", "-allnames")
        val ds : DeckService = mock()
        print(words, ds)
        verify(ds, times(1)).printAllDeckNames()
    }

    @Test
    fun testPrint_invalid() {
        val words = listOf("print", "invalid", "params")
        val ds : DeckService = mock()
        print(words, ds)
        verifyNoInteractions(ds)
    }
}