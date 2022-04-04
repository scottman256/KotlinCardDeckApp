package data

class Deck (val name: String) {
    val cards :MutableList<Card> = mutableListOf()
    val discard: MutableList<Card> = mutableListOf()
    val hands: MutableMap<String, Hand> = mutableMapOf()
    fun shuffle() = cards.shuffle()

    init {
        val suites = arrayOf('♠','♣', '♥','♦')
        val ranks = arrayOf("A","2","3","4","5","6","7","8","9","10","J","K","Q")

        for (rank in ranks)
            for (suite in suites)
                cards.add(Card(rank,suite))
    shuffle()
    }

    override fun toString (): String {
        return cards.toString()
    }

}

