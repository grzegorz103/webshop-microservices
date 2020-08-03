package service.event.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${url.events}")
    private String eventUrl;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(eventUrl))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAll() throws Exception {
        mockMvc.perform(delete(eventUrl))
                .andExpect(status().isNoContent());
    }
}
