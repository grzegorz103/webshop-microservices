package order.service.services.feign;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderOut {

    private Long id;

    private List<Long> productIds;

    private List<ProductDTO> productDTOs;

    private Instant creationDate;

    private BigDecimal totalCost;

    private String userId;

}
