package product.service.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import product.service.ProductServiceApplication;
import product.service.domain.Category;
import product.service.domain.Product;
import product.service.domain.ProductInfo;
import product.service.domain.rabbitmq.CreateEntityInfo;
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

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public ProductController(RestTemplate restTemplate,
                             ProductService productService,
                             RabbitTemplate rabbitTemplate,
                             ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.productService = productService;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    @GetMapping
    public List<Product> findAll() {
        return productService.findAll()
                .stream()
                .map(e -> new ProductInfo(e.getId(), e.getName(), e.getCategoryId(), restTemplate.getForObject("http://CATEGORY-SERVICE/categories/" + e.getCategoryId(), Category.class)))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Product create(@RequestBody Product product) throws JsonProcessingException {
        Product created = productService.create(product);
        rabbitTemplate.convertAndSend("create-exchange",
                "info",
                objectMapper.writeValueAsString(new CreateEntityInfo<>("Create new " + created.getClass().getSimpleName().toLowerCase())));

        return new ProductInfo(created.getId(), created.getName(), created.getCategoryId(),
                restTemplate.getForObject("http://CATEGORY-SERVICE/categories/" + created.getCategoryId(), Category.class)
        );
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product product,
                          @PathVariable("id") Long id) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}
