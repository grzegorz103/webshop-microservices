package order.service.persistence;

import order.service.services.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderProvider {

    Page<OrderDTO> getAll(Pageable pageable);

    OrderDTO getById(Long id);

    OrderDTO save(OrderDTO orderDTO);

    void deleteById(Long id);
}
