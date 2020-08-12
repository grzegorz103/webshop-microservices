package order.service.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import order.service.persistence.OrderState;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    private List<Long> productIds;

    private Instant creationDate;

    private BigDecimal totalCost;

    private String userId;

    private OrderState orderState;

}
