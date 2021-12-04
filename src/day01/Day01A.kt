package day01

fun main() {
    var previousNumber: Int? = null
    var answer = 0
    while (true) {
        val currentNumber = readLine()?.toIntOrNull() ?: break
        previousNumber?.let {
            if (currentNumber > it) {
                ++answer
            }
        }
        previousNumber = currentNumber
    }
    println(answer)
}
