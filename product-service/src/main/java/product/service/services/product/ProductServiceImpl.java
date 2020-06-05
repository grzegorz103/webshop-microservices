package product.service.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.service.persistence.category.CategoryProvider;
import product.service.persistence.product.ProductProvider;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider;

    private final CategoryProvider categoryProvider;

    public ProductServiceImpl(ProductProvider productProvider,
                              RestTemplate restTemplate, CategoryProvider categoryProvider) {
        this.productProvider = productProvider;
        this.categoryProvider = categoryProvider;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productProvider.getAll(pageable);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        productDTO.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        ProductDTO productDTO1 = productDTO;
        return productProvider.save(productDTO);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        ProductDTO fromDb = productProvider.getOne(id);
        fromDb.setName(productDTO.getName());
        fromDb.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        productProvider.save(fromDb);
        return fromDb;
    }

    @Override
    public void delete(Long id) {
        productProvider.delete(id);
    }

}
