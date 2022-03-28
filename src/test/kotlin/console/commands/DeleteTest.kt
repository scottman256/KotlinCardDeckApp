package console.commands

import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import service.DeckService
import kotlin.test.Test

class DeleteTest {

    @Test
    fun testDelete_all() {
        val words = listOf("delete", "-all")
        val ds : DeckService = mock()
        delete(words, ds)
        verify(ds, times(1)).deleteAll()
    }

    @Test
    fun testDelete_deck() {
        val words = listOf("delete", "deck", "testDeck")
        val ds : DeckService = mock()
        delete(words, ds)
        verify(ds, times(1)).deleteDeck("testDeck")
    }

    @Test
    fun testDelete_discard() {
        val words = listOf("delete", "discard", "from","deck","testDeck")
        val ds : DeckService = mock()
        delete(words, ds)
        verify(ds, times(1)).deleteDiscard("testDeck")
    }

    @Test
    fun testDelete_fromHand() {
        val words = listOf("delete", "hand", "testHand","from","deck","testDeck")
        val ds : DeckService = mock()
        delete(words, ds)
        verify(ds, times(1)).deleteFromHand("testDeck","testHand")
    }

    @Test
    fun testDelete_allHands() {
        val words = listOf("delete", "hand", "-all","from","deck","testDeck")
        val ds : DeckService = mock()
        delete(words, ds)
        verify(ds, times(1)).deleteAllHands("testDeck")
    }

    @Test
    fun testDelete_invalid() {
        val words = listOf("delete", "invalid", "params")
        val ds : DeckService = mock()
        delete(words, ds)
        verifyNoInteractions(ds)
    }
}