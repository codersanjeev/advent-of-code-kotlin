/**
 * read a line or return empty string
 */
fun readLn() = readLine() ?: ""

/**
 * read space separated words
 */
fun readLns() = readLn().split(" ")

/**
 * read an integer
 */
fun readInt() = readLn().toInt()

/**
 * read space separated integers
 */
fun readInts() = readLns().map { it.toInt() }

/**
 * read a long integer
 */
fun readLong() = readLn().toLong()

/**
 * read space separated long integers
 */
fun readLongs() = readLns().map { it.toLong() }

/**
 * read a double value
 */
fun readDouble() = readLn().toDouble()

/**
 * read space separated double values
 */
fun readDoubles() = readLns().map { it.toDouble() }