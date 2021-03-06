package product.service.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "price-service"
)
public interface PriceClient {

    @GetMapping("/prices/{id}")
    String getPriceById(@PathVariable("id") Long id);

}


