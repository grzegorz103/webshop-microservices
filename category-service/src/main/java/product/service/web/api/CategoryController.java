package product.service.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.service.domain.Category;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static List<Category> categories;

    //TODO
    static {
        categories = Arrays.asList(
                new Category(1L, "Test1"),
                new Category(2L, "Test1"),
                new Category(3L, "Test1")
        );
    }

    @GetMapping
    public List<Category> getAll() {
        return categories;
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categories.stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
