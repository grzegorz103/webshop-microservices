package product.service.persistence.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product.service.services.product.ProductDTO;

public interface ProductProvider {

    Page<ProductDTO> getAll(Pageable pageable);

    ProductDTO getOne(Long id);

    ProductDTO save(ProductDTO productDTO);

    void delete(Long id);

}
