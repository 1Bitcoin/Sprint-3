package ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WeekendDayExceptionTest {
    @Test
    fun weekendDayExceptionTest() {
        assertThrows(WeekendDayException::class.java) {
            throw WeekendDayException()
        }
    }
}