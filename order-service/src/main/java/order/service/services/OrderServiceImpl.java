package order.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import microservices.common.config.ExchangeNames;
import microservices.common.config.RoutingKeyNames;
import microservices.common.events.EventFactory;
import microservices.common.events.EventPublisher;
import order.service.persistence.Order;
import order.service.persistence.OrderProvider;
import order.service.services.feign.OrderOut;
import order.service.services.feign.ProductDTO;
import order.service.services.feign.ProductFeignClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderProvider orderProvider;

    private final ProductFeignClient productFeignClient;

    private final EventPublisher eventPublisher;

    private final ObjectMapper objectMapper;

    public OrderServiceImpl(OrderProvider orderProvider,
                            ProductFeignClient productFeignClient,
                            EventPublisher eventPublisher, ObjectMapper objectMapper) {
        this.orderProvider = orderProvider;
        this.productFeignClient = productFeignClient;
        this.eventPublisher = eventPublisher;
        this.objectMapper = objectMapper;
    }

    @Override
    public Page<OrderDTO> getAll(Pageable pageable) {
        return orderProvider.getAll(pageable);
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Objects.requireNonNull(orderDTO);
        orderDTO.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        orderDTO.setCreationDate(Instant.now());
        orderDTO.setTotalCost(getCalculatedTotalCost(orderDTO));
        eventPublisher.publish(EventFactory.create("Create new " + Order.class.getSimpleName(), ExchangeNames.EVENT_EXCHANGE, RoutingKeyNames.EVENT_CREATE_KEY));

        return orderProvider.save(orderDTO);
    }

    @Override
    public OrderDTO update(Long id, OrderDTO orderDTO) {
        Objects.requireNonNull(orderDTO);
        return orderProvider.save(orderDTO);
    }

    @Override
    public void delete(Long id) {
        orderProvider.deleteById(id);
    }

    @Override
    public void deleteProductFromOrders(Long productId) {
        orderProvider.deleteProductFromOrders(productId);
    }

    @Override
    public Page<OrderOut> getByUser(Pageable pageable) {
        return orderProvider.getByUser(SecurityContextHolder.getContext().getAuthentication().getName(),
                pageable.isUnpaged()
                        ? PageRequest.of(0, 20)
                        : pageable)
                .map(e -> {
                            e.setProductDTOs(e.getProductIds().stream().map(this::fetchProductById).collect(Collectors.toList()));
                            e.setProductIds(null);
                            return e;
                        }
                );
    }

    private BigDecimal getCalculatedTotalCost(OrderDTO orderDTO) {
        Objects.requireNonNull(orderDTO.getProductIds());

        return orderDTO.getProductIds()
                .stream()
                .map(this::fetchPriceByProductId)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal fetchPriceByProductId(Long productId) {
        return BigDecimal.valueOf(productFeignClient.getProductById(productId).getPrice());
    }

    private ProductDTO fetchProductById(Long productId) {

        return productFeignClient.getProductById(productId);
    }

}
