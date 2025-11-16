import workshop.BuildInfoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = BuildInfoApplication.class)
class BuildInfoApplicationTest {

    @Test
    void contextLoads() {
    }

    @Test
    void mainRunsWithoutThrowing() {
        assertDoesNotThrow(() -> BuildInfoApplication.main(new String[0]));
    }
}
