package product.service.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import product.service.domain.Category;
import product.service.domain.Product;
import product.service.domain.ProductInfo;
import product.service.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final RestTemplate restTemplate;

    private final ProductService productService;

    public ProductController(RestTemplate restTemplate, ProductService productService) {
        this.restTemplate = restTemplate;
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll()
                .stream()
                .map(e -> new ProductInfo(e.getId(), e.getName(), e.getCategoryId(), restTemplate.getForObject("http://CATEGORY-SERVICE/category/" + e.getCategoryId(), Category.class)))
                .collect(Collectors.toList());
    }

}
