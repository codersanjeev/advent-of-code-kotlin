package day12

import getFileReader

fun main() {
    val reader = getFileReader("src/day12/input.txt")
    val adj = hashMapOf<String, MutableList<String>>()
    while (true) {
        val currentLine = reader.readLine()
        if (currentLine.isNullOrEmpty()) break
        val (u, v) = currentLine.split("-")
        if (!adj.containsKey(u)) adj[u] = mutableListOf()
        if (!adj.containsKey(v)) adj[v] = mutableListOf()
        adj[u]?.add(v)
        adj[v]?.add(u)
    }
    val currentPath = mutableListOf("start")
    var pathsCount = 0
    fun dfs() {
        if (currentPath.last() != "end") {
            adj[currentPath.last()]?.forEach { next ->
                if (next.uppercase() == next || !currentPath.contains(next)) {
                    currentPath.add(next)
                    dfs()
                    currentPath.removeLast()
                }
            }
        } else {
            ++pathsCount
        }
    }
    dfs()
    println(pathsCount)
}