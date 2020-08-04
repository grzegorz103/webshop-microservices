package product.service.services.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDTO> findAll(Pageable pageable, String name);

    ProductDTO create(ProductDTO product);

    ProductDTO update(Long id, ProductDTO product);

    void delete(Long id);

    ProductDTO findById(Long id);
}
