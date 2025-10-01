import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    @Test
    void sayHello_shouldReturnGreeting() {
        // given
        HelloService service = new HelloService();

        // when
        String result = service.sayHello("Elias");

        // then
        assertThat(result).isEqualTo("Hello, Elias!");
    }
}
