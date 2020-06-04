package product.service.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.service.domain.Category;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;

@Component
public class Initializer {

    private final CategoryRepository categoryRepository;

    public Initializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void initializeDb() {
        if (categoryRepository.findAll().isEmpty()) {
            Collection<Category> categories = Arrays.asList(
                    new Category(1L, "Test1"),
                    new Category(2L, "Test2"),
                    new Category(3L, "Test3")
            );

            categoryRepository.saveAll(categories);
        }
    }

}
