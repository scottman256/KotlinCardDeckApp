package console.commands

import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import service.DeckService
import kotlin.test.Test

class DrawTest {

    @Test
    fun testDraw() {
        val words = listOf("draw", "5", "cards", "from", "deck", "testDeck", "to", "hand", "testHand")
        val ds : DeckService = mock()
        draw(words, ds)
        verify(ds, times(1)).drawToHand("5","testDeck", "testHand")
    }

    @Test
    fun testDraw_discard() {
        val words = listOf("draw", "5", "cards", "from", "deck", "testDeck")
        val ds : DeckService = mock()
        draw(words, ds)
        verify(ds, times(1)).drawToDiscard("5","testDeck")
    }

    @Test
    fun testDraw_invalid() {
        val words = listOf("draw", "invalid", "params")
        val ds : DeckService = mock()
        draw(words, ds)
        verifyNoInteractions(ds)
    }
}
