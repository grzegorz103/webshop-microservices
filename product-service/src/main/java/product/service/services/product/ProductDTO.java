package product.service.services.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import product.service.services.category.CategoryDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProductDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private CategoryDTO category;

    private Long priceId;

    @NotNull
    private BigDecimal price;

}
