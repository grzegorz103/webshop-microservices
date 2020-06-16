package order.service.services;

import lombok.extern.slf4j.Slf4j;
import order.service.persistence.OrderProvider;
import order.service.services.feign.PriceFeignClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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

    public OrderServiceImpl(OrderProvider orderProvider,
                            PriceFeignClient priceFeignClient) {
        this.orderProvider = orderProvider;
        this.priceFeignClient = priceFeignClient;
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
        return orderProvider.save(orderDTO);
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
            return BigDecimal.valueOf(
                    Double.parseDouble(new JSONObject(priceFeignClient.getProductById(productId)).get("price").toString())
            );
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        throw new IllegalStateException();
    }

}
