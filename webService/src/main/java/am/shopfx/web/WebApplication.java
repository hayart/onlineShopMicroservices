package am.shopfx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableFeignClients
@ServletComponentScan
@SpringBootApplication
@ComponentScan(basePackages = {"am.shopfx.web", "am.shopfx.core"},
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "xyz.defe.sp.common.entity.*"),
		@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "xyz.defe.sp.common.dao.*"),
		@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "xyz.defe.sp.common.service.*")
	}
)
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
