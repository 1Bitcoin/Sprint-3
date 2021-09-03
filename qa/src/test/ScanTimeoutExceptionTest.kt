package ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ScanTimeoutExceptionTest {
    @Test
    fun scanTimeoutExceptionTest() {
        assertThrows(ScanTimeoutException::class.java) {
            throw ScanTimeoutException()
        }
    }
}