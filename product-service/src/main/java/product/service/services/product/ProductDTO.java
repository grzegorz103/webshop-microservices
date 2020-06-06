package product.service.services.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import product.service.services.category.CategoryDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProductDTO {

    private Long id;

    private String name;

    private CategoryDTO category;

}
