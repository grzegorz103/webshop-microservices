package product.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import product.service.persistence.category.Category;
import product.service.persistence.product.Product;
import product.service.services.category.CategoryDTO;
import product.service.services.product.ProductDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toModel(CategoryDTO categoryDTO);

    @Mapping(target = "category", ignore = true)
    ProductDTO toDTO( Product product);

    @Mapping(target = "category", ignore = true)
    Product toModel(ProductDTO productDTO);

}
