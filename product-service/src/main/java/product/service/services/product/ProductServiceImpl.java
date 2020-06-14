package product.service.services.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import microservices.common.config.*;
import microservices.common.events.EventPublisher;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import product.service.services.fallbacks.ProductFallbacks;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
@Transactional
@Primary
public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider;

    private final CategoryProvider categoryProvider;

    private final RestTemplate restTemplate;

    private final EventPublisher eventPublisher;

    private final ProductFallbacks productFallbacks;

    public ProductServiceImpl(ProductProvider productProvider,
                              CategoryProvider categoryProvider,
                              EventPublisher eventPublisher,
                              RestTemplate restTemplate, ProductFallbacks productFallbacks) {
        this.productProvider = productProvider;
        this.categoryProvider = categoryProvider;
        this.eventPublisher = eventPublisher;
        this.restTemplate = restTemplate;
        this.productFallbacks = productFallbacks;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<ProductDTO> price = productProvider.getAll(pageable)
                .map(productDTO -> {
                            try {
                                productDTO.setPrice(
                                        BigDecimal.valueOf(
                                                Double.parseDouble(new JSONObject(productFallbacks.getPriceById(productDTO.getPriceId())).get("price").toString())
                                        ));
                                productDTO.setPriceId(null);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            return productDTO;
                        }
                );

        return price;
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
        eventPublisher.publish(EventFactory.create(productDTO.getPriceId(), ExchangeNames.PRICE_EXCHANGE, RoutingKeyNames.PRICE_DELETE_KEY));
    }

}
