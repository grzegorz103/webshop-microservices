package product.service.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.service.events.EventFactory;
import product.service.events.EventPublisher;
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

    private final EventPublisher eventPublisher;

    public Initializer(CategoryRepository categoryRepository,
                       ProductRepository productRepository,
                       EventPublisher eventPublisher) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }


    @PostConstruct
    public void initDb() {
        Category cat1 = categoryRepository.save(Category.builder().name("cat1").products(new ArrayList<>()).build());
        Category cat2 = categoryRepository.save(Category.builder().name("cat2").products(new ArrayList<>()).build());
        Category cat3 = categoryRepository.save(Category.builder().name("cat3").products(new ArrayList<>()).build());

        productRepository.save(Product.builder().name("test1").category(cat1).priceId(
                (Long) eventPublisher.publishAndReceive(EventFactory.create(4L, "create-exchange", "createPriceKey"))
        ).build());

        productRepository.save(Product.builder().name("test1").category(cat2).priceId(
                (Long) eventPublisher.publishAndReceive(EventFactory.create(5L, "create-exchange", "createPriceKey"))
        ).build());

        productRepository.save(Product.builder().name("test1").category(cat3).priceId(
                (Long) eventPublisher.publishAndReceive(EventFactory.create(6L, "create-exchange", "createPriceKey"))
        ).build());

        productRepository.findAll().stream().forEach(e-> System.out.println(e.getPriceId()));
    }

}
