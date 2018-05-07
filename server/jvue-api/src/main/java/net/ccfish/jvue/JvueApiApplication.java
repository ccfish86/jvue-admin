package net.ccfish.jvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"net.ccfish.jvue.domain.dao", "net.ccfish.jvue.autogen.dao"})
public class JvueApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvueApiApplication.class, args);
	}
}
