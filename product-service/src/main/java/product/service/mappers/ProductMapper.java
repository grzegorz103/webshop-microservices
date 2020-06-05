package product.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import product.service.domain.Product;
import product.service.domain.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    Product toModel(ProductDTO productDTO);

    @Mapping(source = "categoryId", target = "category.id")
    ProductDTO toDTO(Product product);

}
