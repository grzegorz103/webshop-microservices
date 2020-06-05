package product.service.services.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.service.persistence.product.ProductProvider;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider;

    private final RestTemplate restTemplate;

    public ProductServiceImpl(ProductProvider productProvider,
                              RestTemplate restTemplate) {
        this.productProvider = productProvider;
        this.restTemplate = restTemplate;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productProvider.getAll(pageable);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        return productProvider.save(productDTO);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        ProductDTO fromDb = productProvider.getOne(id);
        fromDb.setName(productDTO.getName());
        fromDb.setCategoryDTO(productDTO.getCategoryDTO());
        productProvider.save(fromDb);
        return fromDb;
    }

    @Override
    public void delete(Long id) {
        productProvider.delete(id);
    }

}
