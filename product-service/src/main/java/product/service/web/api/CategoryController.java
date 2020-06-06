package product.service.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import product.service.events.EventFactory;
import product.service.events.EventPublisher;
import product.service.persistence.category.Category;
import product.service.services.category.CategoryDTO;
import product.service.services.category.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final EventPublisher eventPublisher;

    public CategoryController(CategoryService categoryService,
                              EventPublisher eventPublisher) {
        this.categoryService = categoryService;
        this.eventPublisher = eventPublisher;
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
        eventPublisher.publish(EventFactory.create("Create new " + Category.class.getSimpleName(), "create-exchange", "info"));

        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@RequestBody CategoryDTO category,
                              @PathVariable("id") Long id) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

}

