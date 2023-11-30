package com.spjiang.fastfds;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({FdfsClientConfig.class,cn.hutool.extra.spring.SpringUtil.class})
public class StartApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(StartApplication.class, args);
		Object fdfsTestController = run.getBean("FdfsTestController");
	}
}
