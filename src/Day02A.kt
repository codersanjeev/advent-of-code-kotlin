fun main() {
    var forwardAmount = 0
    var depthAmount = 0
    while (true) {
        val input = readLine() ?: break
        val instruction = input.split(" ")
        if (instruction.size < 2) break
        val amount = instruction[1].toInt()
        when (instruction[0]) {
            "forward" -> forwardAmount += amount
            "down" -> depthAmount += amount
            "up" -> depthAmount -= amount
        }
    }
    println(forwardAmount * depthAmount)
}