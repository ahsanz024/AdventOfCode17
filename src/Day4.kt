import java.io.File

fun main(args: Array<String>) {
    D4Task1()
    D4Task2()
}

fun D4Task1() {
    val total = getResult({ words, word ->
        words.drop(1).contains(word)
    })
    println(total)
}

fun D4Task2() {
    val total = getResult({ words, word ->
        val regularizedWord = regularizeWord(word)
        words.drop(1).any { it -> (regularizeWord(it) == regularizedWord) }
    })
    println(total)
}

fun getResult(body: (list: List<String>, word: String ) -> Boolean): Int {
    val total = getLines().fold(0, { acc: Int, l: String ->
        val wordArray = getWordArray(l)
        acc + findValidLines(wordArray, body)
    })
    return total
}
fun findValidLines(words: List<String>, body: (list: List<String>, word: String ) -> Boolean): Int {
    if (words.isEmpty()) return 1
    val word = words[0]
    val res = body(words, word)
    if (res) { return 0 }
    return findValidLines(words.drop(1).toList(), body)
}
fun getWordArray(line: String): List<String> {
    return line.
            split(Regex("\\s"))
}
fun getLines(): List<String> {
    return File("day4.txt").readLines()
}
//////////////////

fun regularizeWord(word: String): String {
    return word.split("").sorted().joinToString("")
}