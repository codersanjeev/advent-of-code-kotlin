package day03

fun main() {
    val submarineData = mutableListOf<String>()
    while (true) {
        val currentLine = readLine() ?: break
        if (currentLine.isEmpty()) break
        submarineData.add(currentLine)
    }
    val dataWidth = submarineData[0].length
    var oxygenData = submarineData.toMutableList()
    var carbonData = submarineData.toMutableList()
    for (columnIndex in 0 until dataWidth) {
        if (oxygenData.size > 1) {
            val zeros = oxygenData.count { it[columnIndex] == '0' }
            val ones = oxygenData.count { it[columnIndex] == '1' }
            oxygenData = if (ones >= zeros) {
                oxygenData.filter {
                    it[columnIndex] == '1'
                } as MutableList<String>
            } else {
                oxygenData.filter {
                    it[columnIndex] == '0'
                } as MutableList<String>
            }
        }
        if (carbonData.size > 1) {
            val zeros = carbonData.count { it[columnIndex] == '0' }
            val ones = carbonData.count { it[columnIndex] == '1' }
            carbonData = if (ones >= zeros) {
                carbonData.filter {
                    it[columnIndex] == '0'
                } as MutableList<String>
            } else {
                carbonData.filter {
                    it[columnIndex] == '1'
                } as MutableList<String>
            }
        }
    }
    println(oxygenData[0].toInt(2) * carbonData[0].toInt(2))
}