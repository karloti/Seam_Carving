fun main() {
    val n = readLine()!!.toInt()
    val (a, b) = List(2) { readLine()!!.toInt() }.sorted()
    println(n in a..b)
}
