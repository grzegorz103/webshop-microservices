package order.service.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import order.service.services.OrderDTO;
import order.service.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Value("${url.orders}")
    private String orderUrl;

    @Test
    void getAllTest() throws Exception {
        when(orderService.getAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(new OrderDTO(), new OrderDTO())));

        mockMvc.perform(get(orderUrl))
                .andExpect(status().isOk());
    }

    @Test
    void saveTest() throws Exception {
        when(orderService.create(any(OrderDTO.class)))
                .thenReturn(new OrderDTO());

        mockMvc.perform(post(orderUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new OrderDTO())))
                .andExpect(status().isCreated());
    }

    @Test
    void updateTest() throws Exception {
        when(orderService.update(anyLong(), any(OrderDTO.class)))
                .thenReturn(new OrderDTO());

        mockMvc.perform(put(orderUrl + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new OrderDTO())))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete(orderUrl + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new OrderDTO())))
                .andExpect(status().isNoContent());
    }

}
