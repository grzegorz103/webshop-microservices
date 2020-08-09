package product.service.services.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import microservices.common.config.*;
import microservices.common.events.EventPublisher;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import product.service.events.EventFactory;
import product.service.persistence.category.CategoryProvider;
import product.service.persistence.product.Product;
import product.service.persistence.product.ProductProvider;
import product.service.services.feign.OrderClient;
import product.service.services.feign.PriceClient;
import product.service.web.filters.ProductFilter;

import java.math.BigDecimal;
import java.util.Collections;

@Service
@Transactional
@Primary
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider;

    private final CategoryProvider categoryProvider;

    private final RestTemplate restTemplate;

    private final EventPublisher eventPublisher;

    private final PriceClient priceClient;

    private final OrderClient orderClient;


    public ProductServiceImpl(ProductProvider productProvider,
                              CategoryProvider categoryProvider,
                              EventPublisher eventPublisher,
                              RestTemplate restTemplate,
                              PriceClient priceClient,
                              OrderClient orderClient) {
        this.productProvider = productProvider;
        this.categoryProvider = categoryProvider;
        this.eventPublisher = eventPublisher;
        this.restTemplate = restTemplate;
        this.priceClient = priceClient;
        this.orderClient = orderClient;
    }

    @Override
    @HystrixCommand(fallbackMethod = "findAllFallback")
    public Page<ProductDTO> findAll(Pageable pageable, ProductFilter productFilter) {
        return productProvider.getAll(pageable, productFilter);
    }

    public Page<ProductDTO> findAllFallback(Pageable pageable, ProductFilter productFilter) {
        log.warn("Using fallback method");
        return new PageImpl<>(Collections.emptyList());
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        productDTO.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        eventPublisher.publish(
                EventFactory.create("Create new " + Product.class.getSimpleName(), ExchangeNames.EVENT_EXCHANGE, RoutingKeyNames.EVENT_CREATE_KEY)
        );

        return productProvider.save(productDTO);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        ProductDTO fromDb = productProvider.getOne(id);
        fromDb.setName(productDTO.getName());
        fromDb.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        fromDb.setPrice(productDTO.getPrice());
        return productProvider.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        ProductDTO productDTO = productProvider.getOne(id);
        productProvider.delete(id);
        orderClient.deleteProductFromOrders(id);
    }

    @Override
    public ProductDTO findById(Long id) {
        return productProvider.getOne(id);
    }

}
