package product.service.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "order-service"
)
public interface OrderClient {

    @DeleteMapping("/orders/product/{productId}")
    void deleteProductFromOrders(@PathVariable("productId") Long productId);

}
