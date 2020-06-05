package product.service.persistence.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import product.service.mappers.CategoryMapper;
import product.service.services.category.CategoryDTO;

@Component
public class CategoryProviderImpl implements CategoryProvider {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryProviderImpl(CategoryRepository categoryRepository,
                                CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Page<CategoryDTO> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toDTO);
    }

    @Override
    public CategoryDTO getOne(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public CategoryDTO save(CategoryDTO productDTO) {
        return categoryMapper.toDTO(
                categoryRepository.save(
                        categoryMapper.toModel(productDTO)
                )
        );
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
