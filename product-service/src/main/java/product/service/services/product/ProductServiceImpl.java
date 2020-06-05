package product.service.services.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import product.service.events.Event;
import product.service.events.EventInfo;
import product.service.events.EventPublisher;
import product.service.persistence.category.CategoryProvider;
import product.service.persistence.product.ProductProvider;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider;

    private final CategoryProvider categoryProvider;

    private final EventPublisher eventPublisher;

    public ProductServiceImpl(ProductProvider productProvider,
                              CategoryProvider categoryProvider,
                              EventPublisher eventPublisher) {
        this.productProvider = productProvider;
        this.categoryProvider = categoryProvider;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productProvider.getAll(pageable);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        productDTO.setCategory(categoryProvider.getOne(productDTO.getCategory().getId()));
        eventPublisher.publish(new Event<>(new EventInfo<>("Create cos"),"create-exchange","info"));
        return productProvider.save(productDTO);
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
