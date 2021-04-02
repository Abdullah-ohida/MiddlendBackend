package EcommerceApp.Spring.boot.Project.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "product")

public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private boolean active;

    public Product(String productName, String productDescription, BigDecimal price, boolean active) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.active = active;
    }
}
