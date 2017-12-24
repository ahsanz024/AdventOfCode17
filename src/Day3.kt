import kotlin.math.absoluteValue


fun main(args: Array<String>) {
    D3Task1()
//    D3Task2()
}

fun D3Task2() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
    return base.plus(8.times(i))
}
fun findQuarter(dVal: Int, qDiff: Int): Int {
    val temp = dVal.minus(qDiff)
    if ( temp > searchValue) {
        return findQuarter(temp, qDiff)
    }
    return temp
}