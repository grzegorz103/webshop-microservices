package product.service.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import product.service.services.product.ProductDTO;
import product.service.services.product.ProductService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping(value = "${url.product}", produces = "application/json")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable,
                                    @RequestParam(value = "name", required = false) String name) {
        return productService.findAll(pageable,name);
    }

    @GetMapping("/{id}")
    public ProductDTO fineById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody @Valid ProductDTO product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody @Valid ProductDTO product,
                             @PathVariable("id") Long id) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}
