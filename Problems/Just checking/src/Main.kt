import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val numbers = List(scanner.nextInt()) { scanner.nextInt() }
    val (p, m) = List(2) { scanner.nextInt() }
    println(numbers.zipWithNext().firstOrNull { it == p to m || it == m to p }?.let { "YES" } ?: "NO")
}