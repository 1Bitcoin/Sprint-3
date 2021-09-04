import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.qa.WeekendDayException

internal class WeekendDayExceptionTest {
    @Test
    fun weekendDayExceptionTest() {
        assertThrows(WeekendDayException::class.java) {
            throw WeekendDayException()
        }
    }
}