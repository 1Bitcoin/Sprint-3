package ru.sber.nio

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


internal class GrepTest {
    private val grep = Grep()

    @ParameterizedTest
    @MethodSource("findTestParams")
    fun find(subString: String) {
        assertDoesNotThrow { grep.find(subString) }
    }

    companion object {
        @JvmStatic
        fun findTestParams() = listOf(
                "22/Jan/2001:14:27:46",
                "192.168.0.1",
                "192.168.1.1"
        )
    }
}