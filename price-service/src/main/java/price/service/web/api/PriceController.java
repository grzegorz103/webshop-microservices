package price.service.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import price.service.services.price.PriceDTO;
import price.service.services.price.PriceService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public Page<PriceDTO> getAll(Pageable pageable) {
        return priceService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public PriceDTO getById(@PathVariable("id") Long id) {
        return priceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDTO create(@RequestBody PriceDTO priceDTO) {
        return priceService.create(priceDTO);
    }

    @PutMapping("/{id}")
    public PriceDTO update(@RequestBody PriceDTO priceDTO) {
        return priceService.update(priceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        priceService.delete(id);
    }

    @GetMapping(value = "/{productId}/price",produces = "application/json")
    public BigDecimal getProductPrice(@PathVariable("productId") Long productId) {
        return priceService.getProductPrice(productId);
    }
}
