package console.commands

import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import service.DeckService
import kotlin.test.Test

class ShuffleTest {

    @Test
    fun testShuffle_deck() {
        val words = listOf("shuffle", "deck", "testDeck")
        val ds : DeckService = mock()
        shuffle(words, ds)
        verify(ds, times(1)).shuffle("testDeck")
    }

    @Test
    fun testShuffle_allCards() {
        val words = listOf("shufflet", "deck", "testDeck", "-all")
        val ds : DeckService = mock()
        shuffle(words, ds)
        verify(ds, times(1)).shuffleAllCards("testDeck")
    }

    @Test
    fun testShuffle_allDecksTest() {
        val words = listOf("shuffle", "-all")
        val ds : DeckService = mock()
        shuffle(words, ds)
        verify(ds, times(1)).shuffleAllDecks()
    }

    @Test
    fun testShuffle_allDecksAllCards() {
        val words = listOf("shuffle", "-all", "-all")
        val ds : DeckService = mock()
        shuffle(words, ds)
        verify(ds, times(1)).shuffleAllDecksAllCards()
    }

    @Test
    fun testShuffle_invalid() {
        val words = listOf("shuffle", "invalid", "params")
        val ds : DeckService = mock()
        shuffle(words, ds)
        verifyNoInteractions(ds)
    }
}