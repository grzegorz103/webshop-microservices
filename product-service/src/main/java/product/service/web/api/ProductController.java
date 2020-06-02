package product.service.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import product.service.domain.Category;
import product.service.domain.Product;
import product.service.domain.ProductInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final RestTemplate restTemplate;

    private static List<Product> products;

    //TODO - dodac repozytoria JPA
    static {
        products = Arrays.asList(
                new Product(1L, "test", 1L),
                new Product(2L, "test", 2L),
                new Product(3L, "test", 2L)
        );
    }

    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Product> findAll() {
        return products.stream()
                .map(e -> new ProductInfo(e.getId(), e.getName(), e.getCategoryId(), restTemplate.getForObject("http://CATEGORY-SERVICE/category/" + e.getCategoryId(), Category.class)))
                .collect(Collectors.toList());
    }

}
