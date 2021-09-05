package ru.sber.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        val originalFile = "logfile.log"
        val resultFile = "logfile.zip"

        try {
            val zipOutputStream = ZipOutputStream(FileOutputStream(resultFile))
            val fileInputStream = FileInputStream(originalFile)
            var fileBuffer: ByteArray
            val zipEntry = ZipEntry(originalFile)

            fileInputStream.use { fileBuffer = fileInputStream.readBytes() }

            zipOutputStream.use {
                zipOutputStream.putNextEntry(zipEntry)
                zipOutputStream.write(fileBuffer)
                zipOutputStream.closeEntry()
            }
        } catch (exception: Exception) {
            println(exception.message)
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        val originalFile = "logfile.zip"
        val resultFile = "logfile.log"

        try {
            val zipInputStream = ZipInputStream(FileInputStream(originalFile))
            val fileOutputStream = FileOutputStream(resultFile)
            var fileBuffer: ByteArray

            zipInputStream.use {
                zipInputStream.nextEntry
                fileBuffer = zipInputStream.readBytes()
                zipInputStream.closeEntry()
            }

            fileOutputStream.use { fileOutputStream.write(fileBuffer) }

        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}