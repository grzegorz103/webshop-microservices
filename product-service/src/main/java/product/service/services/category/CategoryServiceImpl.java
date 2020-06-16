package product.service.services.category;

import microservices.common.events.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import product.service.events.EventFactory;
import product.service.persistence.category.Category;
import product.service.persistence.category.CategoryProvider;

import java.util.ArrayList;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryProvider categoryProvider;

    private final EventPublisher eventPublisher;

    public CategoryServiceImpl(CategoryProvider categoryProvider, EventPublisher eventPublisher) {
        this.categoryProvider = categoryProvider;
        this.eventPublisher = eventPublisher;
    }

    public Page<CategoryDTO> getAll(Pageable pageable) {
        return categoryProvider.getAll(pageable);
    }

    @Override
    public CategoryDTO getById(Long id) {
        return categoryProvider.getOne(id);
    }

    @Override
    public CategoryDTO create(CategoryDTO category) {
        category.setProducts(new ArrayList<>());
        eventPublisher.publish(EventFactory.create("Create new " + Category.class.getSimpleName(), "create-exchange", "info"));

        return categoryProvider.save(category);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO category) {
        CategoryDTO fromDb = categoryProvider.getOne(id);
        fromDb.setName(category.getName());
        return categoryProvider.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        categoryProvider.delete(id);
    }

}
