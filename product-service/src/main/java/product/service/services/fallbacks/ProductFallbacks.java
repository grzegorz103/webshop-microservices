package product.service.services.fallbacks;

import microservices.common.events.EventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import product.service.persistence.category.CategoryProvider;
import product.service.persistence.product.ProductProvider;
import product.service.services.product.ProductDTO;
import product.service.services.product.ProductServiceImpl;

@Component
public class ProductFallbacks extends ProductServiceImpl {

    public ProductFallbacks(ProductProvider productProvider, CategoryProvider categoryProvider, EventPublisher eventPublisher, RestTemplate restTemplate) {
        super(productProvider, categoryProvider, eventPublisher, restTemplate);
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return super.productProvider.getAll(pageable);
    }
}
