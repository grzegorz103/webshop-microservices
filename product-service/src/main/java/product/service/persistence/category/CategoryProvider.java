package product.service.persistence.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product.service.services.category.CategoryDTO;

public interface CategoryProvider {

    Page<CategoryDTO> getAll(Pageable pageable);

    CategoryDTO getOne(Long id);

    CategoryDTO save(CategoryDTO productDTO);

    void delete(Long id);

}
