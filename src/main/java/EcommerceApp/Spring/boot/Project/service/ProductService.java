package EcommerceApp.Spring.boot.Project.service;

import EcommerceApp.Spring.boot.Project.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService{
        Product findProductByProductId(String productId);
        void addProduct(Product product);
        boolean removeProductByProductId(String productId);

}
