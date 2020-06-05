package product.service.services.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import product.service.persistence.category.CategoryProvider;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryProvider categoryProvider;

    public CategoryServiceImpl(CategoryProvider categoryProvider) {
        this.categoryProvider = categoryProvider;
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
