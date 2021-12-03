fun main() {
    val numbersArray = mutableListOf<Int>()
    while (true) {
        val number = readLine()?.toIntOrNull() ?: break
        numbersArray.add(number)
    }
    var previousSum = numbersArray[0] + numbersArray[1] + numbersArray[2]
    var currentSum = previousSum
    currentSum -= numbersArray[0]
    currentSum += numbersArray[3]
    var answer = if (currentSum > previousSum) 1 else 0
    for (index in 4 until numbersArray.size) {
        currentSum += numbersArray[index]
        currentSum -= numbersArray[index - 3]
        previousSum += numbersArray[index - 1]
        previousSum -= numbersArray[index - 4]
        if (currentSum > previousSum) ++answer
    }
    println(answer)
}
