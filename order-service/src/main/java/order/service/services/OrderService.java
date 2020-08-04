package order.service.services;

import order.service.services.feign.OrderOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<OrderDTO> getAll(Pageable pageable);

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO update(Long id, OrderDTO orderDTO);

    void delete(Long id);

    void deleteProductFromOrders(Long productId);

    Page<OrderOut> getByUser(Pageable pageable);
}
