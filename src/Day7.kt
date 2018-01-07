import java.io.File

fun main(args: Array<String>) {
//    val input = Day7Task1()
//    println(input)
    Day7Task2("tknk")
}

fun Day7Task1(): String {
    return findParent(getParent(lines.first()))
}

fun Day7Task2(input: String) {
    println(findChildren(input))
//    getRootParent
//    getWeightOfParent
    val line = findLineWithParent(input)
    val pWeight = getWeightFromLine(line)
    val children = getChildrenList(line)
    println ("wight $pWeight")
    println ("children $children")
    val listOfChildrenWeights
//    getChildren
//    getWeightsOfChildren (recursively)
}

fun findLineWithParent(parent:String): String {
//

    val line = lines.filter { it.contains(Regex("($parent).*(\\d)"))}
    if (line.isEmpty()) return ""
    return line.first()
}
fun findLineWithChild(child:String) {

}

fun findParent(child: String): String {
    val line = lines.filter { it.contains(Regex("(->).*($child)"))}
    if (line.isEmpty()) return child
    return findParent(getParent(line.first()))
}

fun findChildren(parent: String): List<String> {
    val list = lines.filter { it.contains(Regex("($parent).*(->)")) }
    return getChildrenList(list.first())
}

fun getChildrenList(line: String): List<String> {
    return line.split(Regex("\\s->\\s")).getOrNull(1)?.split(",")?: emptyList()  // .replace(Regex("\\s"), "").split(",")
}

fun getParent(line: String): String {
    return line.split(Regex("\\s")).first()
}

fun getWeightOfParent(parent:String) {

}

fun getWeightFromLine(line: String): Int {
    return (Regex("\\d+").find(line)?.value?:"0").toInt()
}

val lines :List<String> = getTaskInput()
    get() {
        if (field == null) {
            field = getTaskInput()
        }
        return field

    }

fun getTaskInput(): List<String> {
    return File("day7.txt").readLines()
}