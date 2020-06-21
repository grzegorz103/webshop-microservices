package order.service.services;

import lombok.extern.slf4j.Slf4j;
import microservices.common.config.ExchangeNames;
import microservices.common.config.RoutingKeyNames;
import microservices.common.events.EventFactory;
import microservices.common.events.EventPublisher;
import order.service.persistence.Order;
import order.service.persistence.OrderProvider;
import order.service.services.feign.PriceFeignClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderProvider orderProvider;

    private final PriceFeignClient priceFeignClient;

    private final EventPublisher eventPublisher;

    public OrderServiceImpl(OrderProvider orderProvider,
                            PriceFeignClient priceFeignClient,
                            EventPublisher eventPublisher) {
        this.orderProvider = orderProvider;
        this.priceFeignClient = priceFeignClient;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Page<OrderDTO> getAll(Pageable pageable) {
        return orderProvider.getAll(pageable);
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Objects.requireNonNull(orderDTO);
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

    private BigDecimal getCalculatedTotalCost(OrderDTO orderDTO) {
        Objects.requireNonNull(orderDTO.getProductIds());

        return orderDTO.getProductIds()
                .stream()
                .map(this::fetchPriceByProductId)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal fetchPriceByProductId(Long productId) {
        try {
            return new BigDecimal(new JSONObject(priceFeignClient.getProductById(productId)).get("price").toString());
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        throw new IllegalStateException();
    }

}
