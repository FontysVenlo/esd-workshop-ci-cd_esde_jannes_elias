import org.junit.jupiter.api.Test;
import com.example.App;


import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void testAppReturnsOK() {
        String result = "OK";
        assertEquals("OK", result);
    }

    App app = new App();
    @Test
    void testStaus() {
        String status = app.getStatus();
        assertEquals("Meister Lampe",status);
    }
}
