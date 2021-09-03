package ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NotAllowReceiveRequestExceptionTest {
    @Test
    fun notAllowReceiveRequestExceptionTest() {
        assertThrows(NotAllowReceiveRequestException::class.java) {
            throw NotAllowReceiveRequestException()
        }
    }
}