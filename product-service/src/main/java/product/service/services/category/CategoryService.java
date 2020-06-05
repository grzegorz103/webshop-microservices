package product.service.services.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDTO> getAll(Pageable pageable);

    CategoryDTO getById(Long id);

    CategoryDTO create(CategoryDTO category);

    CategoryDTO update(Long id, CategoryDTO category);

    void delete(Long id);

}
