package day03

fun main() {
    val submarineData = mutableListOf<String>()
    while (true) {
        val currentLine = readLine() ?: break
        if (currentLine.isEmpty()) break
        submarineData.add(currentLine)
    }
    var (gammaRate, epsilonRate) = listOf("", "")
    for (columnIndex in submarineData[0].indices) {
        var zerosCount = 0
        var onesCount = 0
        for (rowIndex in submarineData.indices) {
            if (submarineData[rowIndex][columnIndex] == '0') {
                ++zerosCount
            } else {
                ++onesCount
            }
        }
        if (onesCount > zerosCount) {
            gammaRate += "1"
            epsilonRate += "0"
        } else {
            gammaRate += "0"
            epsilonRate += "1"
        }
    }
    println(gammaRate.toInt(2) * epsilonRate.toInt(2))
}