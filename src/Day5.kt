import java.io.File

fun main(args: Array<String>) {
    Day5Task1()
    Day5Task2()
}

fun Day5Task1() {
    hashMap.clear()
    println(findEndOfMaze(0,0, { v -> v.inc() }))
}
fun Day5Task2() {
    hashMap.clear()
    println(findEndOfMaze(0,0, ::getNewValue))
}

/************************************************/
val list = getDay5Data()
// Going for updatecValues hashmap instead of using an array with recursion is because
// of stackover flow errors.
val hashMap = HashMap<Int, Int>()

tailrec fun findEndOfMaze(index: Int, count:Int, body: (value:Int ) -> Int): Int {
    val nextIndex = getValueAtIndex(index, body) + index
    if (nextIndex >= list.size) return count.inc()
    return findEndOfMaze(nextIndex, count.inc(), body)
}

fun getValueAtIndex(index: Int, updatedValue: (value:Int ) -> Int): Int {
    if (hashMap.containsKey(index)) {
        val value = hashMap.getValue(index)
        hashMap.put(index, updatedValue(value))
        return value
    }
    val value = list[index]
    hashMap.put(index, updatedValue(value))
    return value
}

fun getNewValue(value:Int):Int {
    return if (value >= 3) value.dec() else value.inc()
}

fun getDay5Data(): List<Int> {
    return File("day5.txt").readLines().map { it.toInt() }
}

fun updateList(list: List<Int>, currentIndex:Int): List<Int> {
    return list.mapIndexed { index, i ->
        if (index == currentIndex) i.inc()
        else i
    }
}