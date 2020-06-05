package product.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import product.service.persistence.category.Category;
import product.service.persistence.product.Product;
import product.service.services.category.CategoryDTO;
import product.service.services.product.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toModel(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

    @Mapping(target = "products", ignore = true)
    Category toModel(CategoryDTO categoryDTO);

    @Mapping(target = "products", ignore = true)
    CategoryDTO toDTO(Category category);

}
