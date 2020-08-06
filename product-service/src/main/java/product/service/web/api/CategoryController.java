package product.service.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import product.service.services.category.CategoryDTO;
import product.service.services.category.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${url.category}", produces = "application/json")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<CategoryDTO> getAll(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO create(@RequestBody CategoryDTO category) {
                return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@RequestBody @Valid CategoryDTO category,
                              @PathVariable("id") Long id) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

}

