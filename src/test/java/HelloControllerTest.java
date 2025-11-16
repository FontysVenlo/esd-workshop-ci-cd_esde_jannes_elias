import workshop.api.HelloController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    @Test
    void home_returnsHelloFromDocker() {
        HelloController helloController = new HelloController();
        String message = helloController.home();

        assertEquals("Your Container was built successfully!", message);
    }
}
