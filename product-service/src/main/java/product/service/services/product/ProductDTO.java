package product.service.services.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import product.service.persistence.category.CategoryDTO;
import product.service.services.category.CategoryDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private CategoryDTO categoryDTO;

}
