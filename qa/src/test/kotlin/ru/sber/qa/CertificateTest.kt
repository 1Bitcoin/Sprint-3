package ru.sber.qa

import io.mockk.mockkClass
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

internal class CertificateTest {
    private val certificateRequest = mockkClass(CertificateRequest::class)

    @BeforeEach
    fun setUp() {
    }

    @ParameterizedTest
    @MethodSource("getCertificateRequestTestParams")
    fun getCertificateRequestTest(processedBy: Long, data: ByteArray) {

        // given
        val certificate = Certificate(certificateRequest, processedBy, data)

        // result
        assertEquals(certificateRequest, certificate.certificateRequest)
    }

    @ParameterizedTest
    @MethodSource("getProcessedByTestParams")
    fun getProcessedByTest(processedBy: Long, data: ByteArray) {

        // given
        val certificate = Certificate(certificateRequest, processedBy, data)

        // result
        assertEquals(processedBy, certificate.processedBy)
    }

    @ParameterizedTest
    @MethodSource("getDataTestParams")
    fun getDataTest(processedBy: Long, data: ByteArray) {

        // given
        val certificate = Certificate(certificateRequest, processedBy, data)

        // result
        assertEquals(data, certificate.data)
    }

    companion object {
        @JvmStatic
        fun getCertificateRequestTestParams() = listOf(
                Arguments.of(23L, Random.nextBytes(100)),
                Arguments.of(23565L, Random.nextBytes(100)),
                Arguments.of(0L, Random.nextBytes(100))
        )

        @JvmStatic
        fun getProcessedByTestParams() = listOf(
                Arguments.of(9000000000000L, Random.nextBytes(100)),
                Arguments.of(9L, Random.nextBytes(100)),
                Arguments.of(768L, Random.nextBytes(100)),
                Arguments.of(10L, Random.nextBytes(100)),
        )

        @JvmStatic
        fun getDataTestParams() = listOf(
                Arguments.of(234234234, Random.nextBytes(100)),
                Arguments.of(121212121L, Random.nextBytes(100)),
                Arguments.of(1111, Random.nextBytes(100)),
                Arguments.of(0L, Random.nextBytes(100)),
        )
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}