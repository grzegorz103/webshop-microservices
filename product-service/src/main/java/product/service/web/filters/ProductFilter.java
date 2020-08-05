package product.service.web.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter {

    private String name;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

}
