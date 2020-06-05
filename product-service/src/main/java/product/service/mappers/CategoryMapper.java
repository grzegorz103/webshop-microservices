package product.service.mappers;

import org.mapstruct.Mapper;
import product.service.persistence.category.Category;
import product.service.services.category.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toModel(CategoryDTO categoryDTO);

}
