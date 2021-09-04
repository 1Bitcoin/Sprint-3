import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.sber.qa.ScanTimeoutException
import ru.sber.qa.Scanner
import kotlin.random.Random
import kotlin.test.assertEquals

internal class ScannerTest {
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
    fun getScanDataTest(timeOut: Long, byteArray: ByteArray) {

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
                Arguments.of(5L, Random.nextBytes(100)),
                Arguments.of(9999L, Random.nextBytes(100))
        )
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}