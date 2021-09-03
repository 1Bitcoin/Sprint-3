package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

internal class CertificateRequestTest {
    @BeforeEach
    fun setUp() {
        mockkObject(Scanner)
        val defaultArrayBytes: ByteArray = byteArrayOf(111, 45, 1, 4)
        every { Scanner.getScanData() } returns defaultArrayBytes
    }

    @ParameterizedTest
    @MethodSource("processParams")
    fun process(hrEmployeeNumber: Long, certificateType: CertificateType) {

        //given
        val certificateRequest = CertificateRequest(hrEmployeeNumber, certificateType)

        // when
        val expected = Certificate(certificateRequest, hrEmployeeNumber, Scanner.getScanData())
        val actual = certificateRequest.process(hrEmployeeNumber)

        // result
        assertEqualsCertificate(expected, actual)
    }

    companion object {
        @JvmStatic
        fun processParams() = listOf(
                Arguments.of(1L, CertificateType.NDFL),
                Arguments.of(245555L, CertificateType.LABOUR_BOOK),
                Arguments.of(24L, CertificateType.LABOUR_BOOK)
        )
    }

    @Test
    fun getEmployeeNumber() {
    }

    @Test
    fun getCertificateType() {
    }

    fun assertEqualsCertificate(excepted: Certificate, actual: Certificate) {
        Assertions.assertEquals(excepted.certificateRequest, actual.certificateRequest)
        Assertions.assertEquals(excepted.data, actual.data)
        Assertions.assertEquals(excepted.processedBy, actual.processedBy)
    }
}