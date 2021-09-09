package ru.sber.nio

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.useLines

/**
 * Реализовать простой аналог утилиты grep с использованием классов из пакета java.nio.
 */
class Grep {
    /**
     * Метод должен выполнить поиск подстроки subString во всех файлах каталога logs.
     * Каталог logs размещен в данном проекте (io/logs) и внутри содержит другие каталоги.
     * Результатом работы метода должен быть файл в каталоге io(на том же уровне, что и каталог logs), с названием result.txt.
     * Формат содержимого файла result.txt следующий:
     * имя файла, в котором найдена подстрока : номер строки в файле : содержимое найденной строки
     * Результирующий файл должен содержать данные о найденной подстроке во всех файлах.
     * Пример для подстроки "22/Jan/2001:14:27:46":
     * 22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] "POST /files HTTP/1.1" 200 - "-"
     */
    fun find(subString: String) {
        val outputFilename = "result.txt"
        val sourceDirectory = "logs"

        val sourcePath = Paths.get(sourceDirectory)
        val outputFile = File(outputFilename)

        try {
            outputFile.bufferedWriter().use { outputStream ->
                Files.walk(sourcePath)
                        .filter { file -> Files.isRegularFile(file) }
                        .forEach { file ->
                            file.useLines { lines ->
                                lines.filter { line -> line.contains(subString) }
                                        .forEach { string ->
                                            outputStream.write(("${file.fileName} : " +
                                                    "${file.useLines { it.indexOf(string) + 1 }} : $string\n"))
                                        }
                            }
                        }
            }
        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}
