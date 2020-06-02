package product.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ProductInfo extends Product {

    private Category category;

    public ProductInfo(Long id, String name, Long categoryId, Category category) {
        super(id, name, categoryId);
        this.category = category;
    }
}
