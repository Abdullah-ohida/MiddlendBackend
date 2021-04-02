package EcommerceApp.Spring.boot.Project.service;

import EcommerceApp.Spring.boot.Project.exception.InvalidIdException;
import EcommerceApp.Spring.boot.Project.exception.ProductException;
import EcommerceApp.Spring.boot.Project.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;
    Product product = new Product("Heklele", "New product", new BigDecimal(500), false);

    @Test
    void canAddProductToDB(){
        productService.addProduct(product);
        assertNotNull(product.getProductId());
        assertEquals(productService.findProductByProductId(product.getProductId()), product);
    }

    @Test
    void addNullProductThrowProductServiceException(){
        product = null;
        try{
           productService.addProduct(product);
        }catch (ProductException err){
            err.printStackTrace();
        }
        assertNull(product);
    }

    @Test
    void findProductWithInvalidId(){
        Product product = new Product("Heklele", "New product", new BigDecimal(500), false);
        productService.addProduct(product);
        assertThrows(InvalidIdException.class, ()-> productService.findProductByProductId("78778788238jkds"));
    }

    @Test
    void addProductWithNullNameNameThrowAProductServiceException(){
        product.setProductName(null);
        assertThrows(ProductException.class, ()-> productService.addProduct(product));
    }

    @Test
    void addProductWithEmptyNameThrowAProductServiceException(){
        product.setProductName("");
        assertThrows(ProductException.class, ()-> productService.addProduct(product));
    }

    @Test
    void addProductWithNullPriceThrowAProductServiceException(){
        product.setPrice(null);
        assertThrows(ProductException.class, ()-> productService.addProduct(product));
    }

    @Test
    void canAddMoreThanOneProduct(){
        Product newProduct = new Product("Amoke food", "new food", new BigDecimal(200), false);
        Product jean = new Product("Breaks jean", "Fashioned", new BigDecimal(1000), false);

        productService.addProduct(newProduct);
        productService.addProduct(jean);

        assertNotNull(newProduct.getProductId());
        assertNotNull(jean.getProductId());
    }

    @Test
    void removeProductFromDatabase(){
        Product newProduct = new Product("Amoke food", "new food", new BigDecimal(200), false);
        Product jean = new Product("Breaks jean", "Fashioned", new BigDecimal(1000), false);

        productService.addProduct(newProduct);
        productService.addProduct(jean);

       boolean isValid = productService.removeProductByProductId(jean.getProductId());
       assertTrue(isValid);
    }

}