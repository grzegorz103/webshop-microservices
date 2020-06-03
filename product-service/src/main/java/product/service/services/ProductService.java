package product.service.services;

import product.service.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product create(Product product);

}
