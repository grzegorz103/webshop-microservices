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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProductDTO {

    private Long id;

    @NotBlank
    @Length(max = 250)
    private String name;

    @NotNull
    private CategoryDTO category;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String description;

    @NotNull
    private List<String> images;

}
