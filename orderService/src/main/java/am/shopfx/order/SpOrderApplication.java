package am.shopfx.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"am.shopfx.order.dao", "am.shopfx.core.dao"})
@EntityScan(basePackages = {"xyz.defe.sp.common.entity.general", "xyz.defe.sp.common.entity.spOrder"})
@SpringBootApplication(scanBasePackages = {"am.shopfx.order.dao", "am.shopfx.core.dao"})
public class SpOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpOrderApplication.class, args);
	}

}
