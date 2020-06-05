package product.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import product.service.domain.dto.CategoryDTO;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ProductInfo extends Product {

    private CategoryDTO category;

    public ProductInfo(Long id, String name, Long categoryId, CategoryDTO category) {
        super(id, name, categoryId);
        this.category = category;
    }
}
