fun main() {
    val str = readLine()!!
    var result = str.first().toString()
    var count = 0
    for (c in str)
        if (c == result.last())
            count++
        else {
            result += "" + count + c
            count = 1
        }
    result += count
    println(result)
}


/*
fun foo1(str: String) {
    var result = str.first().toString()
    var count = 0
    for (c in str)
        if (c == result.last())
            count++
        else {
            result += "" + count + c
            count = 1
        }
    result += count
    println(result)
}

fun foo2(str: String) =
    Regex("""(.)\1*""")
        .findAll(str) // str="aaaabbcaa"
        .map { it.groupValues[0] } // [aaaa, bb, c, aa]
        .map { "" + it[0] + it.length } // [a4, b2, c1, a2]
        .joinToString("") // "a4b2c1a2"
        .let(::println)

fun main() {
    val str = readLine()!!
    when ((1..2).random()) {
        1 -> foo1(str) // variant1 - dynamic programing
        2 -> foo2(str) // variant2 - functional programing
    }
}*/
