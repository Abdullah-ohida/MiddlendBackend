package EcommerceApp.Spring.boot.Project.repository;

import EcommerceApp.Spring.boot.Project.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {

}
