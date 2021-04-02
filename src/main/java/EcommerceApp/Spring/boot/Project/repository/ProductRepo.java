package EcommerceApp.Spring.boot.Project.repository;

import EcommerceApp.Spring.boot.Project.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends MongoRepository<Product, String> {
    Product findProductByProductId(String productId);
    boolean removeByProductId(String productId);
    Iterable<Product> findByProductNameContaining(String productName);
    List<Product> findProductByActive();
}
