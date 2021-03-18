fun main() {
    val beyondTheWall = readLine()!!.split(',').map { it }.toTypedArray()
    val backFromTheWall = readLine()!!.split(',').map { it }.toTypedArray()

    // println(beyondTheWall contentEquals backFromTheWall)
    // This solution not work property if they are in a shuffled order!!!

    // println((beyondTheWall subtract backFromTheWall.toList()).isEmpty())
    // is good solution but, I need to remove whitespaces, because in #2,#3 line, split delimiter not work very well

    println((beyondTheWall.map { it.trim() } subtract backFromTheWall.map { it.trim() }).isEmpty())
    // finally this is universal solution
}