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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

        List<String> images = Arrays.asList(
                "https://elcopcbonline.com/photos/product/4/176/4.jpg",
                "https://cdn.shopify.com/s/files/1/0070/7032/files/camera_56f176e3-ad83-4ff8-82d8-d53d71b6e0fe.jpg",
                "https://petapixel.com/assets/uploads/2017/03/product1.jpeg",
                "https://multimedia.bbycastatic.ca/multimedia/products/500x500/137/13798/13798894.jpg"
        );

        IntStream.range(0, 10)
                .forEach(e -> {
                    Collections.shuffle(images);
                    productRepository.save(Product.builder().images(images).name("test1").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").category(cat1).price(BigDecimal.valueOf(100.50)).build());
                });
        Collections.shuffle(images);
        productRepository.save(Product.builder().images(images).name("test1").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").category(cat1).price(BigDecimal.TEN).build());

        Collections.shuffle(images);
        productRepository.save(Product.builder().images(images).name("test1").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").category(cat2).price(BigDecimal.TEN).build());

        Collections.shuffle(images);
        productRepository.save(Product.builder().images(images).description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").name("test1").category(cat3).price(BigDecimal.TEN).build());
    }

}
