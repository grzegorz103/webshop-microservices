package product.service.persistence;

import org.springframework.stereotype.Component;
import product.service.persistence.category.Category;
import product.service.persistence.category.CategoryRepository;
import product.service.persistence.product.Product;
import product.service.persistence.product.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class Initializer {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public Initializer(CategoryRepository categoryRepository,
                       ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @PostConstruct
    public void initDb() {
        Category cat1 = categoryRepository.save(Category.builder().name("cat1").products(new ArrayList<>()).build());
        Category cat2 = categoryRepository.save(Category.builder().name("cat2").products(new ArrayList<>()).build());
        Category cat3 = categoryRepository.save(Category.builder().name("cat3").products(new ArrayList<>()).build());

        productRepository.save(Product.builder().name("test1").category(cat1).build());
        productRepository.save(Product.builder().name("test2").category(cat2).build());
        productRepository.save(Product.builder().name("test2").category(cat3).build());
    }

}
