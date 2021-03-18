fun main() {
    var have = readLine()!!.toInt()
    var need = 0
    println(readLine()!!
        .split(" ")
        .asSequence()
        .map(String::toInt)
        .runningFold(have) { acc, i -> have = acc; need = i; acc - i }
        .firstOrNull { it < 0 } // Does not calculate all purchases if you have "money" <0
        ?.let { "Error, insufficient funds for the purchase. You have $have, but you need $need." }
        ?: "Thank you for choosing us to manage your account! You have ${have - need} money.")
}