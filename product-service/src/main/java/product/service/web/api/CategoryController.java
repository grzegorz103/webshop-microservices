package product.service.web.api;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import product.service.services.category.CategoryDTO;
import product.service.services.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final RabbitTemplate rabbitTemplate;

    public CategoryController(CategoryService categoryService,
                              RabbitTemplate rabbitTemplate) {
        this.categoryService = categoryService;
        this.rabbitTemplate = rabbitTemplate;
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
    public CategoryDTO create(@RequestBody CategoryDTO category) {
        rabbitTemplate.convertAndSend("create-exchange", "info", "Create new category");
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

