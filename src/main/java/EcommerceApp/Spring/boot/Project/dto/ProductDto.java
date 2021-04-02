package EcommerceApp.Spring.boot.Project.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
@Data
@NoArgsConstructor

public class ProductDto {
        @Id
        private String productId;
        private String productName;
        private String productDescription;
        private BigDecimal price;

        public ProductDto(String productName, String productDescription, BigDecimal price) {
            this.productName = productName;
            this.productDescription = productDescription;
            this.price = price;
        }

}
