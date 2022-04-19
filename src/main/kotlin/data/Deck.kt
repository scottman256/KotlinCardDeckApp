package data

import data.enum.SuiteType

class Deck (val name: String, val type : SuiteType = SuiteType.CHARACTER) {
    val cards :MutableList<Card> = mutableListOf()
    val discard: MutableList<Card> = mutableListOf()
    val hands: MutableMap<String, Hand> = mutableMapOf()
    fun shuffle() = cards.shuffle()

    init {

        // if can be used as an expression, and can be used to replace the ternary operator x ? y : z from Java.
        val suites = if(type==SuiteType.CHARACTER) arrayOf('S','C', 'H','D') else arrayOf('♠','♣', '♥','♦')
        val ranks = arrayOf("A","2","3","4","5","6","7","8","9","10","J","K","Q")

        for (rank in ranks)
            for (suite in suites)
                cards.add(Card(rank,suite))
        shuffle()
    }

    // as Deck is not a data class, a toString() method needs to be defined.
    override fun toString (): String {
        return cards.toString()
    }

}

