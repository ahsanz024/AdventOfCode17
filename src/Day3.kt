

import kotlin.math.absoluteValue
import kotlin.math.max

fun main(args: Array<String>) {
    D3Task1()
    D3Task2()
}

//val searchValueTask2 = 30
val searchValueTask2 = 347991
fun D3Task2() {
    searchData(0, 0,  emptyList<Int>().toMutableList())
}

fun searchData(index: Int, acc: Int, list: MutableList<Int>): Int {
    val sum = calcSum(index, list).plus(list.getOrElse(index) {0})
    list.add(sum)
    if (sum > searchValueTask2) {
        println("SUM "+sum)
        return sum
    }
    return searchData(index + 1, sum, list)
}

fun calcSum(index: Int, list: MutableList<Int>): Int {
    if (index == 0) return 1
    val pos = calcPosition(index)
    return findSumOfNeighbors(index, pos.first, pos.second, list)
}
fun findSumOfNeighbors(index:Int, x:Int, y:Int, list: MutableList<Int>):Int {

    val neighbourIndexes:MutableList<Int> = emptyList<Int>().toMutableList()

    (x - 1).rangeTo(x + 1).forEach({ xPos ->
        (y - 1).rangeTo(y + 1).forEach({ yPos ->
            val neighbourIndex = calcIndex(xPos, yPos)
            neighbourIndexes.add(neighbourIndex)
        })

    })
    return neighbourIndexes.fold(0) { acc, elem ->
        if (elem >= index) acc else (acc + list.getOrElse(elem) {0})
    }
}

fun calcLevel(x: Int, y: Int): Int = max(x.absoluteValue, y.absoluteValue)

fun calcLevelByIndex(index: Int, ref: Int = 1, level:Int = 0): Int {
    if (index < ref) return level
    return calcLevelByIndex(index, ref.plus(getSquareSize(level.inc())), level.inc())
}

fun calcIndex(x:Int, y: Int): Int {

    val level = calcLevel(x, y)
    if (x == 0 && y == 0) return 0

    val qSize = getQsize(level)
    val incValue = if (level <= 0) 0 else getRange(level - 1)
    when {
        x == level && y >= (1 - level) -> return (level + y - 1).plus(incValue)
        y == level && x <= (level - 1) -> return (level + qSize - x - 1).plus(incValue)
        x == -level && y <= (level - 1) -> return (level + qSize.times(2) - y - 1).plus(incValue)
        y == -level && x >= (1 - level) -> return (x + (level + qSize.times(3)) - 1).plus(incValue)
    }
    return -1
}
fun calcPosition(i: Int): Pair<Int, Int> {
    if (i < 1) return Pair(0, 0)

    val level = calcLevelByIndex(i)
    val index = (getRange(level - 1) - i).absoluteValue
    val qSize = getQsize(level)
    when {
        index in 0..qSize.dec() -> return Pair(level, (index - level + 1))
        index >= qSize && index < qSize.times(2) -> return Pair(level + qSize - index - 1, level)
        index >= qSize.times(2) && index < qSize.times(3) -> return Pair(-level, level + qSize.times(2) - index - 1)
    }
    return Pair((index - (level + qSize.times(3)) + 1), -level)
}


fun getRange(level: Int, acc: Int = 1): Int {
    if (level > 0) return getRange(level.dec(), acc + getSquareSize(level))
    return acc
}

fun getQsize(level: Int): Int = level.times(2)

fun getSquareSize(index:Int): Int = 8.times(index)


val searchValue = 347991

fun D3Task1() {
    val diagonal = getDiagIndexAndValue(0, 1)
    val shortestDistance = diagonal.first
    val diagonalValue = diagonal.second
    val quarterDifference = shortestDistance.times(2)

    val qVal = findQuarter(diagonalValue, quarterDifference)
    val dist = shortestDistance + (qVal.plus(quarterDifference.div(2))).minus(searchValue).absoluteValue
    println("dist $dist ")

}
fun getDiagIndexAndValue(i: Int, acc:Int): Pair<Int, Int> {
    val sum = calcDiagValue(i, acc)
    if ( sum < searchValue ) return getDiagIndexAndValue(i + 1, sum)
    return Pair(i, sum)
}
fun calcDiagValue(i: Int, base: Int): Int = base.plus(getSquareSize(i))

fun findQuarter(dVal: Int, qDiff: Int): Int {
    val temp = dVal.minus(qDiff)
    if (temp > searchValue) return findQuarter(temp, qDiff)
    return temp
}