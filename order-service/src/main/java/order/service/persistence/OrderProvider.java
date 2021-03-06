package order.service.persistence;

import order.service.services.OrderDTO;
import order.service.services.feign.OrderOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderProvider {

    Page<OrderOut> getAll(Pageable pageable);

    OrderDTO getById(Long id);

    OrderDTO save(OrderDTO orderDTO);

    void deleteById(Long id);

    void deleteProductFromOrders(Long productId);

    Page<OrderOut> getByUser(String name, Pageable pageable);
}
