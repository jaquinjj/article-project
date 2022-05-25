package com.clink.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.clink.blog.conf.SpringSecurityConfig;

@Import({ SpringSecurityConfig.class })
@EnableJpaRepositories
@ComponentScan
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ClinkBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinkBlogApplication.class, args);
	}

 
}
