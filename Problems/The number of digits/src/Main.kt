fun main() = readLine()!!.length.let(::println)

fun main1() = println(
    when (readLine()!!.length) {
        1 -> 1
        2 -> 2
        3 -> 3
        4 -> 4
        else -> ""
    }
)

fun main2() {
    when (readLine()!!.length) {
        1 -> println("1")
        2 -> println("2")
        3 -> println("3")
        4 -> println("4")
    }
}