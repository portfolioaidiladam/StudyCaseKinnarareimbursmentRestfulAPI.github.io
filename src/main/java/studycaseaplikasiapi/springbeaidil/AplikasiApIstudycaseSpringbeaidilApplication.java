package studycaseaplikasiapi.springbeaidil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "studycaseaplikasiapi.springbeaidil")
public class AplikasiApIstudycaseSpringbeaidilApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AplikasiApIstudycaseSpringbeaidilApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(AplikasiApIstudycaseSpringbeaidilApplication.class, args);
	}

}
