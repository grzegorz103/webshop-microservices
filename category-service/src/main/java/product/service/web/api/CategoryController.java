package product.service.web.api;

import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import product.service.domain.Category;
import product.service.services.CategoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        rabbitTemplate.convertAndSend("create-exchange", "info", "Create new category");
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public Category update(@RequestBody Category category,
                           @PathVariable("id") Long id) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

}
