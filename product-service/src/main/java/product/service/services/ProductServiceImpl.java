package product.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.service.domain.Product;
import product.service.domain.dto.CategoryDTO;
import product.service.domain.dto.ProductDTO;
import product.service.persistence.ProductRepository;
import product.service.persistence.providers.ProductProvider;

import java.util.List;

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
        Page<ProductDTO> products = productProvider.getAll(pageable);
        products.get()
                .forEach(this::fetchCategory);
        return products;
    }

    private void fetchCategory(ProductDTO productDTO) {
        productDTO.setCategory(
                restTemplate.getForObject("http://CATEGORY-SERVICE/categories/" + productDTO.getCategory().getId(), CategoryDTO.class)
        );
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        ProductDTO saved = productProvider.save(productDTO);
        if (saved.getCategory() != null) {
            this.fetchCategory(saved);
        }

        return saved;
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        ProductDTO fromDb = productProvider.getOne(id);
        fromDb.setName(productDTO.getName());
        fromDb.setCategory(productDTO.getCategory());
        productProvider.save(fromDb);
        this.fetchCategory(fromDb);
        return fromDb;
    }

    @Override
    public void delete(Long id) {
        productProvider.delete(id);
    }

}
