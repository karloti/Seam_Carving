import kotlin.math.sqrt

fun main() = when (readLine()!!) {
    "triangle" -> {
        val (a, b, c) = List(3) { readLine()!!.toDouble() }
        ((a + b + c) / 2).let { sqrt(it * (it - a) * (it - b) * (it - c)) }
    }
    "rectangle" -> {
        val (a, b) = List(2) { readLine()!!.toDouble() }
        a * b
    }
    "circle" -> {
        val r = readLine()!!.toDouble()
        3.14 * r * r
    }
    else -> 0.0
}.let(::println)

