package net.ccfish.jvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"net.ccfish.jvue.domain.dao", "net.ccfish.jvue.autogen.dao"})
public class JvueApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvueApiApplication.class, args);
	}
}
