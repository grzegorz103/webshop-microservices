package product.service.services.fallbacks;

import microservices.common.events.EventPublisher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import product.service.persistence.category.CategoryProvider;
import product.service.persistence.product.ProductProvider;
import product.service.services.product.ProductDTO;
import product.service.services.product.ProductServiceImpl;

@FeignClient(
        name = "price-service"
)
public interface ProductFallbacks {

    @GetMapping("/prices/{id}")
    String getPriceById(@PathVariable("id") Long id);

}
