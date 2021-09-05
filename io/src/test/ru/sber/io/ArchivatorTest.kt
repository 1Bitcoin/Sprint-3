package ru.sber.io

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class ArchivatorTest {
    private val archivator = Archivator()

    @Test
    fun zipLogfileTest() {
        assertDoesNotThrow { archivator.zipLogfile() }
    }

    @Test
    fun unzipLogfileTest() {
        assertDoesNotThrow { archivator.unzipLogfile() }
    }
}