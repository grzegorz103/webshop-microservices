package order.service.services;

import order.service.persistence.OrderProvider;
import order.service.web.api.OrderController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private OrderProvider orderProvider;

    @Test
    public void getAll() {
        Page<OrderDTO> orders = new PageImpl<>(Arrays.asList(mock(OrderDTO.class), mock(OrderDTO.class)));
        when(orderProvider.getAll(any(Pageable.class)))
                .thenReturn(orders);

        Page<OrderDTO> all = orderService.getAll(Pageable.unpaged());

        verify(orderProvider, times(1)).getAll(any(Pageable.class));
        assertThat(all).isNotNull();
        assertThat(all.getTotalElements()).isEqualTo(orders.getTotalElements());
    }

    @Test
    void create() {
        when(orderProvider.save(any(OrderDTO.class)))
                .thenReturn(mock(OrderDTO.class));

        OrderDTO orderDTO = orderService.create(mock(OrderDTO.class));

        verify(orderProvider, times(1)).save(any(OrderDTO.class));
        assertThat(orderDTO).isNotNull();
    }

    @Test
    void update() {
        when(orderProvider.save(any(OrderDTO.class)))
                .thenReturn(mock(OrderDTO.class));

        OrderDTO updated = orderService.update(1L, mock(OrderDTO.class));
        verify(orderProvider, times(1)).save(any(OrderDTO.class));

        assertThat(updated).isNotNull();
    }

    @Test
    void delete() {
        orderService.delete(1L);
        verify(orderProvider, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteProductFromOrders() {
        orderService.deleteProductFromOrders(1L);
        verify(orderProvider, times(1)).deleteProductFromOrders(anyLong());
    }
}
