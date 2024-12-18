package am.shopfx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"am.shopfx.product.dao"})
@EntityScan(basePackages = {"am.shopfx.core.entity", "am.shopfx.product.entity" })
@ComponentScan(basePackages = { "am.shopfx.core"},
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "am.shopfx.core.dao.*"),
				@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "am.shopfx.core.service.*")
		}
)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
