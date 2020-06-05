package product.service.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product.service.domain.dto.ProductDTO;

public interface ProductService {

    Page<ProductDTO> findAll(Pageable pageable);

    ProductDTO create(ProductDTO product);

    ProductDTO update(Long id, ProductDTO product);

    void delete(Long id);
}
