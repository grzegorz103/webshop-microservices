package product.service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.service.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
