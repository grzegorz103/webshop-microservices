package product.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import product.service.persistence.product.Product;
import product.service.services.product.ProductDTO;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    Product toModel(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

}
