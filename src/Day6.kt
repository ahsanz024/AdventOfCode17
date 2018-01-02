import java.io.File

fun main(args: Array<String>) {
    Day6Task1(startingList)
//    Day6Task2()
}


val startingList = getDay6Data()
val listOfCombinations = ArrayList<List<Int>>()

fun Day6Task1(list: List<Int>) {
    val max = list.max()!!
    val indexOfMax = list.indexOf(max)
    val count = getListUpdateCount(max, indexOfMax, list)
    println(count)
}

tailrec fun getListUpdateCount(max: Int, indexOfMax: Int, list: List<Int>, count: Int = 0): Int {
    val list1 = breakList(list, indexOfMax).toMutableList()
    val updatedList = joinList(processList(list1, max), indexOfMax)

    if (listOfCombinations.contains(updatedList)) return count.inc()

    listOfCombinations.add(updatedList)
    val newMax = updatedList.max()!!
    val newIndexOfMax = updatedList.indexOf(newMax)
    return getListUpdateCount(newMax, newIndexOfMax, updatedList, count.inc())
}

fun processList(list: MutableList<Int>, max: Int): List<Int> {
    val size = list.size
    for (i in 0 until max) {
        if (i >= size) {
            val temp = i - (i.div(size)).times(size)
            list[temp] += 1
        } else {
            list[i] += 1
        }
    }
    return list
}

fun breakList(list:List<Int>, indexOfMax: Int) :List<Int> {
    val size = list.size
    val firstPart = (if (indexOfMax == size) emptyList() else list.subList(indexOfMax + 1, size))
    val lastPart = (if (indexOfMax <= 0) emptyList() else list.subList(0, indexOfMax))
    return (firstPart + lastPart + 0).toMutableList()
}

fun joinList(updatedList:List<Int>, indexOfMax: Int): List<Int> {
    val size = updatedList.size
    val lastPart = updatedList.subList(0, (size - indexOfMax - 1))
    val firstPart = updatedList.subList(lastPart.size, updatedList.size)
    return (firstPart + lastPart)
}

fun Day6Task2() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

fun getDay6Data(): List<Int> {
    return File("day6.txt").readLines().first().split(Regex("\\s")).map { it.toInt() }
}