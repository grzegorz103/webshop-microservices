package product.service.persistence.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product.service.services.product.ProductDTO;
import product.service.web.filters.ProductFilter;

public interface ProductProvider {

    Page<ProductDTO> getAll(Pageable pageable, ProductFilter productFilter);

    ProductDTO getOne(Long id);

    ProductDTO save(ProductDTO productDTO);

    void delete(Long id);

}
