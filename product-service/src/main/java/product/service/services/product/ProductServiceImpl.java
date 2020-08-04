package product.service.services.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import microservices.common.config.*;
import microservices.common.events.EventPublisher;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
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

import java.math.BigDecimal;

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
    public Page<ProductDTO> findAll(Pageable pageable, String name) {
        Page<ProductDTO> price = productProvider.getAll(pageable, name)
                .map(this::mapPriceIdToPrice);

        return price;
    }

    private Page<ProductDTO> findAllFallback(Pageable pageable, String name) {
        log.warn("Using findAll fallback method");
        return productProvider.getAll(pageable, null);
    }

    private ProductDTO mapPriceIdToPrice(ProductDTO productDTO) {
        productDTO.setPrice(fetchPriceById(productDTO.getPriceId()));
        productDTO.setPriceId(null);
        return productDTO;
    }

    private BigDecimal fetchPriceById(Long priceId) {
        try {
            return BigDecimal.valueOf(
                    Double.parseDouble(new JSONObject(priceClient.getPriceById(priceId)).get("price").toString())
            );
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        throw new IllegalStateException();
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        productDTO.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        eventPublisher.publish(
                EventFactory.create("Create new " + Product.class.getSimpleName(), ExchangeNames.EVENT_EXCHANGE, RoutingKeyNames.EVENT_CREATE_KEY)
        );

        Long createdPriceId = (Long) eventPublisher.publishAndReceive(
                EventFactory.create(productDTO.getPrice(), ExchangeNames.PRICE_EXCHANGE, RoutingKeyNames.PRICE_CREATE_KEY)
        );

        productDTO.setPriceId(createdPriceId);

        return productProvider.save(productDTO);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        ProductDTO fromDb = productProvider.getOne(id);
        fromDb.setName(productDTO.getName());
        fromDb.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        fromDb.setPriceId(productDTO.getPriceId());
        return productProvider.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        ProductDTO productDTO = productProvider.getOne(id);
        productProvider.delete(id);
        orderClient.deleteProductFromOrders(id);
        eventPublisher.publish(EventFactory.create(productDTO.getPriceId(), ExchangeNames.PRICE_EXCHANGE, RoutingKeyNames.PRICE_DELETE_KEY));
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductDTO one = productProvider.getOne(id);
        return mapPriceIdToPrice(one);
    }

}
