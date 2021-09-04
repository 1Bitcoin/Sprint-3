package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random
import kotlin.test.assertEquals

internal class ScannerTest {
    private val byteArray = Random.nextBytes(100)

    @BeforeEach
    fun setUp() {
        mockkObject(Random)
    }

    @ParameterizedTest
    @MethodSource("getScanDataScanTimeoutExceptionTestParams")
    fun getScanDataScanTimeoutExceptionTest(timeOut: Long) {

        // given
        every { Random.nextLong(5000L, 15000L) } returns timeOut

        // result
        Assertions.assertThrows(ScanTimeoutException::class.java) { Scanner.getScanData() }
    }

    @ParameterizedTest
    @MethodSource("getScanDataTestParams")
    fun getScanDataTest(timeOut: Long) {

        // given
        every { Random.nextLong(5000L, 15000L) } returns timeOut
        every { Random.nextBytes(100) } returns byteArray

        // result
        assertEquals(byteArray, Scanner.getScanData())
    }

    companion object {
        @JvmStatic
        fun getScanDataScanTimeoutExceptionTestParams() = listOf(
                Arguments.of(11000L),
                Arguments.of(23000L)
        )

        @JvmStatic
        fun getScanDataTestParams() = listOf(
                Arguments.of(5L),
                Arguments.of(9999L)
        )
    }
}