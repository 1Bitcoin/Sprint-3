import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.qa.NotAllowReceiveRequestException

internal class NotAllowReceiveRequestExceptionTest {
    @Test
    fun notAllowReceiveRequestExceptionTest() {
        assertThrows(NotAllowReceiveRequestException::class.java) {
            throw NotAllowReceiveRequestException()
        }
    }
}