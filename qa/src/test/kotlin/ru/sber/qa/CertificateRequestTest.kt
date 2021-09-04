import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.sber.qa.Certificate
import ru.sber.qa.CertificateRequest
import ru.sber.qa.CertificateType
import ru.sber.qa.Scanner
import kotlin.random.Random

internal class CertificateRequestTest {
    @BeforeEach
    fun setUp() {
        mockkObject(Scanner)
    }

    @ParameterizedTest
    @MethodSource("processParams")
    fun process(hrEmployeeNumber: Long, certificateType: CertificateType, data: ByteArray) {

        //given
        val certificateRequest = CertificateRequest(hrEmployeeNumber, certificateType)
        every { Scanner.getScanData() } returns data

        // when
        val expected = Certificate(certificateRequest, hrEmployeeNumber, Scanner.getScanData())
        val actual = certificateRequest.process(hrEmployeeNumber)

        // result
        assertEqualsCertificate(expected, actual)
    }

    companion object {
        @JvmStatic
        fun processParams() = listOf(
                Arguments.of(1L, CertificateType.NDFL, Random.nextBytes(100)),
                Arguments.of(245555L, CertificateType.LABOUR_BOOK, Random.nextBytes(100)),
                Arguments.of(24L, CertificateType.LABOUR_BOOK, Random.nextBytes(100))
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

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}