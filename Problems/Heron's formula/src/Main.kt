import kotlin.math.sqrt

fun main() {
    val nums = IntArray(3) { readLine()!!.toInt() }
    val (a, b, c) = nums
    val p = nums.sum() / 2.0
    val heron = sqrt(p * (p - a) * (p - b) * (p - c))
    println(heron)
}