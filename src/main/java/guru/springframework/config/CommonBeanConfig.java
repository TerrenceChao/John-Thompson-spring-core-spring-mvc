package guru.springframework.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("guru.springframework.repositories")
public class CommonBeanConfig {

	@Bean
	public StrongPasswordEncryptor strongEncryptor() {
		return new StrongPasswordEncryptor();
	}
}
