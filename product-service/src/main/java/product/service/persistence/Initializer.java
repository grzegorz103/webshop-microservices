package product.service.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.service.domain.Product;

import javax.annotation.PostConstruct;

@Component
public class Initializer {

    private final ProductRepository productRepository;

    public Initializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void initDb() {
        productRepository.save(Product.builder().name("test1").categoryId(1L).build());
        productRepository.save(Product.builder().name("test2").categoryId(2L).build());
        productRepository.save(Product.builder().name("test2").categoryId(3L).build());
    }

}
