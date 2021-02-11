fun main() = readLine()!!.run { count { it in "gGcC" } * 100.0 / length }.let(::println)

//fun main() {
//
//    fun fibonacci() =
//        generateSequence(0 to 1) { it.second to it.first + it.second }.map { it.first }
//
//    fibonacci()
//        .take(10)
//        .shuffled()
//        .toList()
//        .let(::println)
//}
