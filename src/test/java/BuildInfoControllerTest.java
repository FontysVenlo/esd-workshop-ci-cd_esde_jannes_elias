import workshop.BuildInfoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        classes = BuildInfoApplication.class,
        properties = {
                "app.name=workshop-service",
                "BUILD_VERSION=1234",
                "APP_COMMIT=deadbeefcafebabe00112233"
        }
)
@AutoConfigureMockMvc
class BuildInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void healthIsUp() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void infoContainsExpectedFieldsAndValues() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app").value("workshop-service"))
                .andExpect(jsonPath("$.version").value("1234"))
                .andExpect(jsonPath("$.commit").value("deadbeefcafebabe00112233"))
                .andExpect(jsonPath("$.java", not(isEmptyOrNullString())))
                .andExpect(jsonPath("$.os", not(isEmptyOrNullString())))
                .andExpect(jsonPath("$.startedAt", not(isEmptyOrNullString())))
                .andExpect(jsonPath("$.uptime", not(isEmptyOrNullString())));
    }
}
