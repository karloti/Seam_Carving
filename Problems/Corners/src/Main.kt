fun main() {
    //Do not touch code below
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toTypedArray()
        inputArray += strings
    }
    //write your code here
    print(inputArray.first().first() + " ")
    println(inputArray.first().last())
    print(inputArray.last().first() + " ")
    println(inputArray.last().last())
}