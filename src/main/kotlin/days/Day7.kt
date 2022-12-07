package days

class Day7 : Day(7) {
    override fun partOne(): Any {
        val fileSystem = buildFileSystem()

        return fileSystem.getAllDirectories(fileSystem.root)
            .filter { it.size <= 100000 }
            .sumOf { it.size }
    }

    override fun partTwo(): Any {
        val fileSystem = buildFileSystem()

        val totalSpace = 70000000
        val requiredFreeSpace = 30000000

        val currentUnusedSpace = totalSpace - fileSystem.root.size
        val spaceToFind = requiredFreeSpace - currentUnusedSpace

        return fileSystem.getAllDirectories(fileSystem.root)
            .filter { it.size >= spaceToFind }
            .minOf { it.size }
    }

    private fun buildFileSystem(): FileSystem {
        val fileSystem = FileSystem(Directory("/"))

        val iterator = inputList.listIterator()

        while (iterator.hasNext()) {
            var line = iterator.next().split(" ")
            if (line.first() == "$") {
                when (line[1]) {
                    "cd" -> {
                        when (line.last()) {
                            ".." -> fileSystem.goToParent()
                            "/" -> fileSystem.goToRoot()
                            else -> fileSystem.goToChild(line.last())
                        }
                    }

                    "ls" -> {
                        while (iterator.hasNext()) {
                            var line = iterator.next().split(" ")

                            when (line.first()) {
                                "$" -> {
                                    iterator.previous();
                                    break
                                }

                                "dir" -> fileSystem.createDirectory(line.last())
                                else -> fileSystem.createFile(line.last(), line.first().toInt())
                            }
                        }
                    }
                }
            }
        }
        
        return fileSystem
    }
}


private interface Content {
    val name: String
    val size: Int
}

private class File(override val name: String, override val size: Int = 0) : Content
private class Directory(override val name: String) : Content {

    val content: MutableList<Content> = mutableListOf()
    override val size: Int
        get() = content.getSize()
}

private fun MutableList<Content>.getSize() =
    this.sumOf { it.size }

private data class FileSystem(val root: Directory) {

    private val currentPath: MutableList<Directory> = mutableListOf()
    private val currentDirectory: Directory
        get() = currentPath.lastOrNull() ?: root

    fun goToRoot() = currentPath.clear()

    fun goToParent() {
        if (currentPath.isEmpty()) return
        currentPath.removeLast()
    }

    fun goToChild(name: String) {
        currentPath.add(currentDirectory.content.first { it is Directory && it.name == name } as Directory)
    }

    fun createFile(name: String, size: Int) {
        currentDirectory.content.add(File(name, size))
    }

    fun createDirectory(name: String) {
        currentDirectory.content.add(Directory(name))
    }

    fun getAllDirectories(directory: Directory): List<Directory> {
        if (directory.content.none { it is Directory }) return listOf(directory)
        return directory.content
            .filterIsInstance<Directory>()
            .map { getAllDirectories(it) }
            .flatten() + listOf(directory)

    }
}
