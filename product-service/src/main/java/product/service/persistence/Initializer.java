package product.service.persistence;

import microservices.common.config.ExchangeNames;
import microservices.common.config.RoutingKeyNames;
import microservices.common.events.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.service.events.EventFactory;
import product.service.persistence.category.Category;
import product.service.persistence.category.CategoryRepository;
import product.service.persistence.product.Product;
import product.service.persistence.product.ProductRepository;
import product.service.services.feign.PriceClient;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Component
public class Initializer {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final EventPublisher eventPublisher;

    private final PriceClient priceClient;

    public Initializer(CategoryRepository categoryRepository,
                       ProductRepository productRepository,
                       EventPublisher eventPublisher, PriceClient priceClient) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
        this.priceClient = priceClient;
    }


    @PostConstruct
    public void initDb() {
        Category cat1 = categoryRepository.save(Category.builder().name("cat1").products(new ArrayList<>()).build());
        Category cat2 = categoryRepository.save(Category.builder().name("cat2").products(new ArrayList<>()).build());
        Category cat3 = categoryRepository.save(Category.builder().name("cat3").products(new ArrayList<>()).build());


        IntStream.range(0, 10)
                .forEach(e ->
                        productRepository.save(Product.builder().name("test1").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").category(cat1).price(BigDecimal.valueOf(100.50)).build()
                        )
                );
        productRepository.save(Product.builder().name("test1").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").category(cat1).price(BigDecimal.TEN).build());

        productRepository.save(Product.builder().name("test1").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").category(cat2).price(BigDecimal.TEN).build());

        productRepository.save(Product.builder().description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").name("test1").category(cat3).price(BigDecimal.TEN).build());
    }

}
