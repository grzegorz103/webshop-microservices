package product.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.service.domain.Product;
import product.service.persistence.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product fromDb = productRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        fromDb.setCategoryId(product.getCategoryId());
        fromDb.setName(product.getName());
        return productRepository.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
