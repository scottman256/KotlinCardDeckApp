import data.Card
import data.Hand
import console.commands.create
import console.commands.delete
import console.commands.draw
import console.commands.help
import console.commands.shuffle
import console.commands.print
import service.DeckService

// This is the main function (use fun to declare functions in kotlin), where the console application will begin execution.
// The function takes arguments, although for this program no arguments are used.
// This is an example of "classless code" in Kotlin, as the functions here do not belong to a class.
fun main() {
    println("Welcome to the Kotlin Deck Demo Application!")
    val deckService = DeckService()
 while (true) {
     println("Please enter a command:")
     val command = readLine()
     val words = command?.split(" ")!! //null safety
     // when is like the java switch statement on steroids
     // it is more concise but also supports

    when(words[0]) {
        "create" -> create(words,deckService)
        "delete" -> delete(words,deckService)
        "shuffle" -> shuffle(words,deckService)
        "draw" -> draw(words,deckService)
        "print" -> print(words,deckService)
        "help" -> help()
        "exit", "end", "quit" -> break
        else -> println("You have entered an invalid command")
    }
     println(" ")
 }
    println("Bye bye!")

}

fun playground() {
    val suites = arrayOf('♠','♣', '♥','♦')
    val ranks = arrayOf("A","2","3","4","5","6","7","8","9","10","J","K","Q")

    val list = mutableListOf<Card>()
    for (rank in ranks)
        for (suite in suites)
            list.add(Card(rank,suite))

    list.shuffle()
    for (card in list)
        println(card)

    val hand = mutableListOf<Card>()
    for (i in 0..5)
    {
        hand.add(list[0])
        list.removeAt(0)
    }

    val myHand = Hand(hand,"myHand")

    println(myHand)
    println(list)
}
