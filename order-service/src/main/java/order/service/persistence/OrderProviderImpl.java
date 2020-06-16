package order.service.persistence;

import order.service.mappers.OrderMapper;
import order.service.services.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class OrderProviderImpl implements OrderProvider {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderProviderImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Page<OrderDTO> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::toDTO);
    }

    @Override
    public OrderDTO getById(Long id) {
        return orderMapper.toDTO(
                orderRepository.findById(id)
                        .orElseThrow(IllegalAccessError::new)
        );
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        return orderMapper.toDTO(
                orderRepository.save(
                        orderMapper.toModel(orderDTO)
                )
        );
    }

}
