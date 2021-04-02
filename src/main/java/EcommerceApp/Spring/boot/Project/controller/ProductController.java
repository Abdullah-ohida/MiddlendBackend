package EcommerceApp.Spring.boot.Project.controller;

import EcommerceApp.Spring.boot.Project.model.Product;
import EcommerceApp.Spring.boot.Project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts (@RequestParam(required = false) String productName){
        try{
            List<Product> products = new ArrayList<>();
            if(productName == null)
                productRepo.findAll().forEach(products::add);
            else
                productRepo.findByProductNameContaining(productName).forEach(products::add);
            if (products.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(products, HttpStatus.OK);

        }catch (Exception err){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity <Product> getProductById(@PathVariable("id") String id){
        Optional <Product> product = Optional.ofNullable(productRepo.findProductByProductId(id));

        if(product.isPresent())
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/active")
    public ResponseEntity<List<Product>> findProductByActive(){
        try{
            List<Product> products = productRepo.findProductByActive();
            if(products.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(products, HttpStatus.OK);

        }catch (Exception err){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        try{
            Product newProduct = productRepo.save(new Product(product.getProductName(), product.getProductDescription(), product.getPrice(), false));
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }catch (Exception err){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        Optional<Product> currentProduct = Optional.ofNullable(productRepo.findProductByProductId(id));

        if(currentProduct.isPresent()){
            Product updatedProduct = currentProduct.get();
            updatedProduct.setProductName(product.getProductName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setProductDescription(product.getProductDescription());
            updatedProduct.setActive(product.isActive());
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products")
    public ResponseEntity<HttpStatus> deleteAllProducts(){
        try{
            productRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception err){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id){
        try{
            productRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception err){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
