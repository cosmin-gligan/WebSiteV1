package siit;


import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class Application {

	// - Migrate all the fields from the customer table to the the `/customers` page.
	// - Implement logout. The button already exists in customers list page. Implement a servlet for the URL it goes to. The servlet should call "invalidate()" on the session and redirect the user to the login page.

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletRegistrationBean dbConsole() {
		return new ServletRegistrationBean<>(new WebServlet(), "/db/*");
	}

}
