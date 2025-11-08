import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import workshop.BuildInfoApplication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class BuildInfoApplicationTest {

    @Test
    void contextLoads() {
    }

    @Test
    void mainRunsWithoutThrowing() {
        assertDoesNotThrow(() -> BuildInfoApplication.main(new String[]{}));
    }
}
