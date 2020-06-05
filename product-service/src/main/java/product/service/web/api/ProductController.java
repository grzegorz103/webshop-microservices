package product.service.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import product.service.events.CreateEntityInfo;
import product.service.services.product.ProductDTO;
import product.service.services.product.ProductService;


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
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO product) throws JsonProcessingException {
        ProductDTO created = productService.create(product);
        rabbitTemplate.convertAndSend("create-exchange",
                "info",
                objectMapper.writeValueAsString(new CreateEntityInfo<>("Create new " + ProductDTO.class.getSimpleName().toLowerCase()))
        );

        return created;
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO product,
                             @PathVariable("id") Long id) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}
