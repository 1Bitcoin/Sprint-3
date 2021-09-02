package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CertificateRequestTest {
    @BeforeEach
    fun setUp() {
        mockkObject(Scanner)
        val defaultArrayBytes: ByteArray = byteArrayOf(111, 45, 1, 4)
        every { Scanner.getScanData() } returns defaultArrayBytes
    }

    @Test
    fun process() {
        // given
        val hrEmployeeNumber = 1L
        val certificateRequest = CertificateRequest(hrEmployeeNumber, CertificateType.NDFL)

        // when
        val expected = Certificate(certificateRequest, hrEmployeeNumber, Scanner.getScanData())
        val actual = certificateRequest.process(hrEmployeeNumber)

        // result
        assertEqualsCertificate(expected, actual)
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