import java.io.File

fun main(args: Array<String>) {
    D4Task1()
    D4Task2()
}

fun D4Task1() {
    val lines = File("day4.txt").readLines()
    val total = lines.fold(0, { acc: Int, l: String ->
        val wordArray = getWordArray(l)
        acc + (if(findWordInLine(wordArray)) 0 else 1)
    })
    println(total)
}
fun findWordInLine(words: List<String>): Boolean {
    if (words.isEmpty()) return false
    val word = words[0]
    val res = words.drop(1).contains(word)
    if (res) { return true }
    return findWordInLine(words.drop(1).toList())
}

fun D4Task2() {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

fun getWordArray(line: String): List<String> {
    return line.
            split(Regex("\\s"))
}
