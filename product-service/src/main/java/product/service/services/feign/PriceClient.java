package product.service.services.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import product.service.persistence.product.ProductProvider;
import product.service.services.product.ProductDTO;

@FeignClient(
        name = "price-service"
)
public interface PriceClient {

    @GetMapping("/prices/{id}")
    String getPriceById(@PathVariable("id") Long id);

}


