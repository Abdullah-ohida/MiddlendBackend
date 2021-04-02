package EcommerceApp.Spring.boot.Project.service;

import EcommerceApp.Spring.boot.Project.exception.InvalidIdException;
import EcommerceApp.Spring.boot.Project.exception.ProductException;
import EcommerceApp.Spring.boot.Project.model.Product;
import EcommerceApp.Spring.boot.Project.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepo productRepo;

    @Override
    public Product findProductByProductId(String productId) {
        if(productRepo.findProductByProductId(productId) == null) throw new InvalidIdException("No product with this specific id");
        return productRepo.findProductByProductId(productId);
    }

    @Override
    public void addProduct(Product product) throws ProductException{
        isValidProduct(product);
        productRepo.save(product);
    }

    @Override
    public boolean removeProductByProductId(String productId) {
        return productRepo.removeByProductId(productId);
    }

    private void isValidProduct(Product product) throws ProductException{
        if(product == null){
            throw new ProductException("Cannot add null product");
        }
        if(product.getProductName() == null || product.getProductName().equals("")){
            throw new ProductException("Product name is required");
        }
        if(product.getPrice() == null){
            throw new ProductException("Price is required");
        }
    }
}
