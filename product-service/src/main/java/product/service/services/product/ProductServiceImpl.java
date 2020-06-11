package product.service.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.service.events.CreatePriceEvent;
import product.service.events.EventFactory;
import product.service.events.EventPublisher;
import product.service.persistence.category.CategoryProvider;
import product.service.persistence.product.Product;
import product.service.persistence.product.ProductProvider;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider;

    private final CategoryProvider categoryProvider;

    private final RestTemplate restTemplate;

    private final EventPublisher eventPublisher;

    public ProductServiceImpl(ProductProvider productProvider,
                              CategoryProvider categoryProvider,
                              EventPublisher eventPublisher,
                              RestTemplate restTemplate) {
        this.productProvider = productProvider;
        this.categoryProvider = categoryProvider;
        this.eventPublisher = eventPublisher;
        this.restTemplate = restTemplate;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productProvider.getAll(pageable)
                .map(productDTO -> {
                            productDTO.setPrice(restTemplate.getForObject("http://PRICE-SERVICE/prices/" + productDTO.getId() + "/price", BigDecimal.class));
                            return productDTO;
                        }
                );
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        productDTO.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        eventPublisher.publish(
                EventFactory.create("Create new " + Product.class.getSimpleName(), "create-exchange", "info")
        );

        ProductDTO saved = productProvider.save(productDTO);
        saved.setPrice(productDTO.getPrice());

        eventPublisher.publish(
                EventFactory.create(new CreatePriceEvent(saved.getId(), saved.getPrice()), "create-exchange", "createPriceKey")
        );

        return saved;
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        ProductDTO fromDb = productProvider.getOne(id);
        fromDb.setName(productDTO.getName());
        fromDb.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        return productProvider.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        productProvider.delete(id);
    }

}
