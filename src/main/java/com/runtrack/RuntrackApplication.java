package com.runtrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RuntrackApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RuntrackApplication.class, args);
		System.out.println(context.containsBean("userRepository")); // 检查 Repository 是否被加载
		System.out.println(context.containsBean("userService")); // 检查 Service 是否被加载
	}
}

