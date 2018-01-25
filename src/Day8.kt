import java.io.File

fun main(args: Array<String>) {
    Day8Task1()
    println("Task2 Max : ${Day8Task2()}")
}

fun Day8Task1() {
    val hashMap = HashMap<String, Int>()
    getTask8Input().forEach { it ->
        processFunc(hashMap, it)
    }
    println("Task1 Max : ${hashMap.values.max()}")
}

fun Day8Task2(): Int {
    val hashMap = HashMap<String, Int>()
   return getTask8Input().fold(0, {acc, it ->
        val newVal = processFunc(hashMap, it)
        if (newVal > acc) newVal
        else acc
    })
}

fun processFunc(hashMap: HashMap<String, Int>, it: String): Int {
    val elements = processLine(it)
    val reg2Val = hashMap.getOrDefault(reg2(elements), 0)
    val reg1Name = reg1(elements)
    val reg1Val = hashMap.getOrDefault(reg1Name, 0)
    if (checkCondition(reg2Val, elements)) {
        val updatedVal = applyOp(reg1Val, elements)
        hashMap.put(reg1Name, updatedVal)
        return updatedVal
    }
    return reg1Val
}

fun reg1(list: List<String>) = list[0]
fun reg2(list: List<String>) = list[4]

fun val1(list: List<String>) = list[2]
fun val2(list: List<String>) = list[6]

fun op1(list: List<String>) = list[1]
fun op2(list: List<String>) = list[5]

fun applyOp(val1:Int, elements: List<String>): Int {
    val val2Apply = val1(elements).toInt()
    val op = op1(elements)
    when (op) {
        "inc" -> return (val1 + val2Apply)
        "dec" -> return (val1 - val2Apply)
        else -> {
            println("applyOp else $op")
            return val1
        }
    }
}

fun checkCondition(val1:Int, list: List<String>): Boolean {
    val op = op2(list)
    val val2 = val2(list).toInt()
    when (op) {
        "==" -> return (val1 == val2)
        ">=" -> return (val1 >= val2)
        "<=" -> return (val1 <= val2)
        "!=" -> return (val1 != val2)
        ">" -> return (val1 > val2)
        "<" -> return (val1 < val2)
        else -> {
            println("checkCondition else $op")
            return false
        }
    }
}

fun processLine(command: String): List<String> {
    return command.split(Regex("\\s"))
}

fun getTask8Input(): List<String> {
    return File("day8.txt").readLines()
}