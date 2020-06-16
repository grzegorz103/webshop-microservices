package order.service.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<OrderDTO> getAll(Pageable pageable);

    OrderDTO create(OrderDTO orderDTO);

}
