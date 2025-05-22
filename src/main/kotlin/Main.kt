package org.example

interface FileSystem {
    fun delete()
    fun printStructure()
}

class File(
    private val filename: String,
): FileSystem {
    override fun delete() {
        println("Deleting file: $filename")
    }

    override fun printStructure() {
        println(filename)
    }

}

class Directory(
    private val directoryName: String,
): FileSystem {
    private val children = mutableListOf<FileSystem>()

    override fun delete() {
        println("Deleting directory $directoryName: ")
        for (child in children) {
            child.delete()
        }
    }

    override fun printStructure() {
        println(children.joinToString("\n"))
    }

    fun addFile(file: List<File>) {
        file.forEach {
            children.add(it)
        }
    }
}

class Cliente {
    fun rmrf(file: FileSystem) {
        file.delete()
    }
}

fun main() {
    val cliente = Cliente()
    val apoo = Directory(directoryName = "apoo")
    val file1 = File(filename = "Refactoring.md")
    val file2 = File(filename = "Como aumentar o antebraço.json")
    val file3 = File(filename = "Design Patterns.md")
    val file4 = File(filename = "Clash of clans base atualizada.txt")
    apoo.addFile(listOf(file1, file2, file3))

    cliente.rmrf(file4)
    cliente.rmrf(apoo)
}