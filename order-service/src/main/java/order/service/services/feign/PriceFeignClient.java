package order.service.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service"
)
public interface PriceFeignClient {

    @GetMapping("/products/{id}")
    String getProductById(@PathVariable("id") Long id);

}


