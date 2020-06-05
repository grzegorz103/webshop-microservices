package product.service.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.service.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



}
