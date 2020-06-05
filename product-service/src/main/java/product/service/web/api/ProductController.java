package product.service.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import product.service.services.product.ProductDTO;
import product.service.services.product.ProductService;


@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO product) {
        return productService.create(product);
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
