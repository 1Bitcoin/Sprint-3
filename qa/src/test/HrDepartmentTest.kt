package ru.sber.qa

import io.mockk.every
import io.mockk.mockkClass
import io.mockk.mockkStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.*


internal class HrDepartmentTest {
    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDateTime::class)
    }

    @ParameterizedTest
    @MethodSource("receiveRequestParams")
    fun receiveRequest(currentDay: DayOfWeek, certificateRequest: CertificateRequest) {

        // given
        every { LocalDateTime.now(Clock.systemUTC()).dayOfWeek } returns currentDay

        // result
        Assertions.assertThrows(WeekendDayException::class.java) { HrDepartment.receiveRequest(certificateRequest) }
    }

    companion object {
        @JvmStatic
        fun receiveRequestParams() = listOf(
                Arguments.of(DayOfWeek.SATURDAY, CertificateRequest(23L, CertificateType.NDFL)),
                Arguments.of(DayOfWeek.SUNDAY, CertificateRequest(2222L, CertificateType.LABOUR_BOOK))
        )
    }

    @Test
    fun processNextRequest() {
    }

    @AfterEach
    fun tearDown() {

    }
}