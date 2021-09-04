package ru.sber.qa

import io.mockk.every
import io.mockk.mockkClass
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.*


internal class HrDepartmentTest {
    private val certificateRequest = mockkClass(CertificateRequest::class)

    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDateTime::class)
    }

    @ParameterizedTest
    @MethodSource("receiveRequestWeekendDayExceptionTestParams")
    fun receiveRequestWeekendDayExceptionTest(currentDay: DayOfWeek) {

        val certificateRequest = mockkClass(CertificateRequest::class)

        // given
        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay

        // result
        assertThrows(WeekendDayException::class.java) { HrDepartment.receiveRequest(certificateRequest) }
    }

    @ParameterizedTest
    @MethodSource("receiveRequestNotAllowReceiveRequestExceptionTestParams")
    fun receiveRequestNotAllowReceiveRequestExceptionTest(currentDay: DayOfWeek, certificateType: CertificateType) {

        // given
        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        // result
        assertThrows(NotAllowReceiveRequestException::class.java) { HrDepartment.receiveRequest(certificateRequest) }

    }

    @ParameterizedTest
    @MethodSource("receiveRequestTestParams")
    fun receiveRequestTest(currentDay: DayOfWeek, certificateType: CertificateType) {

        // given
        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType

        // result
        assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) }
    }

    @ParameterizedTest
    @MethodSource("processNextRequestParams")
    fun processNextRequestTest(currentDay: DayOfWeek, certificateType: CertificateType, hrEmployeeNumber: Long) {

        // given
        val certificate = mockkClass(Certificate::class)

        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay
        every { certificateRequest.certificateType } returns certificateType
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        // when
        HrDepartment.receiveRequest(certificateRequest)

        // result
        assertDoesNotThrow { HrDepartment.processNextRequest(hrEmployeeNumber) }
    }

    companion object {
        @JvmStatic
        fun receiveRequestWeekendDayExceptionTestParams() = listOf(
                Arguments.of(DayOfWeek.SATURDAY),
                Arguments.of(DayOfWeek.SUNDAY)
        )

        @JvmStatic
        fun receiveRequestNotAllowReceiveRequestExceptionTestParams() = listOf(
                Arguments.of(DayOfWeek.TUESDAY, CertificateType.NDFL),
                Arguments.of(DayOfWeek.THURSDAY, CertificateType.NDFL),
                Arguments.of(DayOfWeek.MONDAY, CertificateType.LABOUR_BOOK),
                Arguments.of(DayOfWeek.WEDNESDAY, CertificateType.LABOUR_BOOK),
                Arguments.of(DayOfWeek.FRIDAY, CertificateType.LABOUR_BOOK)
        )

        @JvmStatic
        fun receiveRequestTestParams() = listOf(
                Arguments.of(DayOfWeek.TUESDAY, CertificateType.LABOUR_BOOK),
                Arguments.of(DayOfWeek.THURSDAY, CertificateType.LABOUR_BOOK),
                Arguments.of(DayOfWeek.MONDAY, CertificateType.NDFL),
                Arguments.of(DayOfWeek.WEDNESDAY, CertificateType.NDFL),
                Arguments.of(DayOfWeek.FRIDAY, CertificateType.NDFL)
        )

        @JvmStatic
        fun processNextRequestParams() = listOf(
                Arguments.of(DayOfWeek.TUESDAY, CertificateType.LABOUR_BOOK, 111L),
                Arguments.of(DayOfWeek.THURSDAY, CertificateType.LABOUR_BOOK, 232L),
                Arguments.of(DayOfWeek.MONDAY, CertificateType.NDFL, 0L),
                Arguments.of(DayOfWeek.WEDNESDAY, CertificateType.NDFL, -1L),
                Arguments.of(DayOfWeek.FRIDAY, CertificateType.NDFL, 45L)
        )
    }
}