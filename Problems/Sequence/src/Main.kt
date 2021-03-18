fun main() = List(3) { readLine()!!.first() }
    .let { it[0] + 1 == it[1] && it[1] + 1 == it[2] }
    .let(::println)