package console.commands

import org.mockito.kotlin.*
import service.DeckService
import kotlin.test.Test


class CreateTest {

    @Test
    fun testCreate() {
        val words = listOf("create", "deck", "testDeck")
        val ds : DeckService = mock()
        create(words, ds)
        verify(ds,times(1)).addDeck("testDeck")
    }

    @Test
    fun testCreate_invalid() {
        val words = listOf("create", "invalid", "params")
        val ds : DeckService = mock()
        create(words, ds)
        verifyNoInteractions(ds)
    }
}