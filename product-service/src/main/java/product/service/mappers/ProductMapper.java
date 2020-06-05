package product.service.mappers;

import org.mapstruct.Mapper;
import product.service.domain.Product;
import product.service.domain.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toModel(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

}
