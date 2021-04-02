package EcommerceApp.Spring.boot.Project;

import EcommerceApp.Spring.boot.Project.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootTest
@EnableMongoRepositories("EcommerceApp.Spring.boot.Project.repository")
class SpringBootProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
