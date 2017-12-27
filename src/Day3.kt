import kotlin.math.absoluteValue
import kotlin.math.max


fun main(args: Array<String>) {
//    D3Task1()
    D3Task2()
}

val searchValue2 = 330
fun D3Task2() {
    searchData(0)
}

fun searchData(index: Int) {
//    for (i in 0..getSquareSize(index)) {
//
//    }
//    println(calcPosition(1,2))
//    println(calcPosition(2,2))
//    println(calcPosition(3,2))
//    println(calcPosition(4,2))
//
//    println(calcPosition(5,2))
//    println(calcPosition(6,2))
//    println(calcPosition(7,2))
//    println(calcPosition(8,2))
//
//    println(calcPosition(9,2))
//    println(calcPosition(10,2))
//    println(calcPosition(11,2))
//    println(calcPosition(12,2))

//    println(calcPosition(13,2))
//    println(calcIndex(-1,-2, 2))
//    println(calcPosition(14,2))
//    println(calcIndex(0,-2, 2))
//    println(calcPosition(15,2))
//    println(calcIndex(1,-2, 2))
//    println(calcPosition(0,2))
//    println(calcIndex(2,-2, 2))
    for (i in 0..28) {
        val pos = calcPosition(i)
        println(pos)
        println(calcIndex(pos.first, pos.second))
        println("------------------")
    }

//    println(calcIndex(2,1, 2))
//    println(calcIndex(2,2, 2))

//    findNeighbors(1, 1, listOf(listOf(1)))
}

fun calcLevel(x: Int, y: Int): Int {
    return max(x.absoluteValue, y.absoluteValue)
}
fun calcLevelByIndex(index: Int, ref: Int = 1, level:Int = 0): Int {
    if (index < ref) {
        return level
    }
    return calcLevelByIndex(index, ref.plus(8.times(level.inc())), level.inc())
}

fun calcPosition(i: Int): Pair<Int, Int> {
    val level = calcLevelByIndex(i)
    println("level $level")
    val index = (getRange(level - 1) - i).absoluteValue
    println("index $index")
    val qSize = getQsize(level)
    when {
        index == 0 -> return Pair(level, -level)
        index in 1..qSize -> return Pair(level, (index - level))
        index >= qSize.inc() && index <= qSize.times(2) -> return Pair((level.plus(qSize).minus(index)), level)
        index >= qSize.times(2).inc() && index <= qSize.times(3) -> return Pair(-level, level.plus(qSize.times(2)).minus(index))
        else -> return Pair(index.minus(level.plus(3.times(qSize))), -level)
    }
}

fun getQsize(level: Int): Int {
    return level.times(2)
}

fun calcIndex(x:Int, y: Int): Int {
    val level = calcLevel(x, y)
    val qSize = getQsize(level)
    val incValue = if (level <= 0) 0 else getRange(level - 1)
    println("incValue  $incValue ")
    when {
//        x == level && level > 0 -> return level.plus(y)
        x == level && y >= (level - 1) -> return level.plus(y)
        y == level && x <= (level - 1) -> return (level + qSize - x)
        x == -level && y <= (level - 1) -> return (level + 2.times(qSize) - y)
        y == -level -> return (x +3.times(qSize) + level)
        else -> {
            return -1
        }
    }
}

fun findNeighbors(index: Int, level: Int, arrayList: List<List<Int>>) {
    0.rangeTo(getSquareSize(8)).map {

    }
}

fun getRange(level: Int, acc: Int = 1): Int {
    if (level > 0) {
        return getRange(level.dec(), acc + getSquareSize(level))
    }
    return acc
}

fun getSquareSize(index:Int): Int {
    return 8.times(index)
}


//val searchValue = 9
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
    if ( sum < searchValue ) {
        return getDiagIndexAndValue(i + 1, sum)
    }
    return Pair(i, sum)
}
fun calcDiagValue(i: Int, base: Int): Int {
    return base.plus(getSquareSize(i))
}
fun findQuarter(dVal: Int, qDiff: Int): Int {
    val temp = dVal.minus(qDiff)
    if ( temp > searchValue) {
        return findQuarter(temp, qDiff)
    }
    return temp
}