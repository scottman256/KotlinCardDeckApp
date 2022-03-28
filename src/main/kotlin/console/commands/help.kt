package console.commands

import java.io.File

// You can define a function in one line, using = instead of {}
fun help () = File("src/resources/help.txt").forEachLine { println(it) }