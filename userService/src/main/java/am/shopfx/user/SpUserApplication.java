package am.shopfx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories("am.shopfx.user.dao")
@EntityScan(basePackages = { "am.shopfx.core.entity.spUser" })
@ComponentScan(basePackages = {"am.shopfx.user"},
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "xyz.defe.sp.common.dao.*"),
				@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "xyz.defe.sp.common.service.*")
		}
)
public class SpUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpUserApplication.class, args);
	}

}
