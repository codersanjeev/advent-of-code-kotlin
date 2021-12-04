package day02

fun main() {
    var forwardAmount = 0
    var depthAmount = 0
    var aim = 0
    while (true) {
        val input = readLine() ?: break
        val instruction = input.split(" ")
        if (instruction.size < 2) break
        val amount = instruction[1].toInt()
        when (instruction[0]) {
            "forward" -> {
                forwardAmount += amount
                depthAmount += aim * amount
            }
            "down" -> aim += amount
            "up" -> aim -= amount
        }
    }
    println(forwardAmount * depthAmount)
}