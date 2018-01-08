import java.io.File

fun main(args: Array<String>) {
    val input = Day7Task1()
    println("Task1: $input")
    Day7Task2(input)
}

fun Day7Task1(): String {
    return findParent(getParentFromLine(lines.first()))
}

fun Day7Task2(input: String) {
    task2Process(input)
}

fun task2Process(input: String): Int {

    val line = findLineWithParent(input)
    val pWeight = getWeightFromLine(line)
    val children = getChildrenListFromLine(line)
    if (children.isEmpty()) return pWeight
    val cWeights = children.map {
        val v = task2Process(it)
        if (v < 0) { return -1 }
        Pair(v, it)
    }.sortedBy { it.first }
    val diff = cWeights.first().first - cWeights.last().first
    if (diff != 0) {
        var uniquePair: Pair<Int, String>? = null
        val size = cWeights.size
        if (cWeights.first().first != cWeights.get(1).first) { uniquePair = cWeights.first() }
        else if (cWeights.last().first != cWeights.get(size - 2).first) { uniquePair = cWeights.last() }
        uniquePair?.let {
            val updatedWeight = getWeightOfParent(uniquePair!!.second) + diff
            println("Task2:  $updatedWeight : Node: ${uniquePair.second}")
            return -1
        }
    }
    return cWeights.fold(pWeight, {
        acc, i -> acc + i.first
    })
}

fun findLineWithParent(parent:String): String {
    val filteredList = lines.filter { it.contains(Regex("($parent).*(\\d)"))}
    return if (filteredList.isNotEmpty()) filteredList.first() else ""
}

fun findParent(child: String): String {
    val line = lines.filter { it.contains(Regex("(->).*($child)"))}
    if (line.isEmpty()) return child
    return findParent(getParentFromLine(line.first()))
}

fun getChildrenListFromLine(line: String): List<String> {
    val ret = line.replace(Regex("\\s+"), "").split(Regex("->"))
    if (ret.size < 2) return emptyList()
    ret.get(1).let { it ->
        return it.split(",")
    }
}

fun getParentFromLine(line: String): String {
    return line.split(Regex("\\s")).first()
}

fun getWeightOfParent(parent:String): Int {
    return getWeightFromLine(findLineWithParent(parent))
}

fun isRootNode(node:String): Boolean {
    val line = lines.filter { it.contains(Regex("(->).*($node)"))}
    return line.isEmpty()
}

fun getWeightFromLine(line: String): Int {
    return (Regex("\\d+").find(line)?.value?:"0").toInt()
}

val lines :List<String> = getTaskInput()


fun getTaskInput(): List<String> {
    return File("day7.txt").readLines()
}