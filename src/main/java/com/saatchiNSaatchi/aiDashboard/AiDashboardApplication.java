package com.saatchiNSaatchi.aiDashboard;

import com.saatchiNSaatchi.aiDashboard.controllers.AdminController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class AiDashboardApplication {
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("AI Usage, by Steve Lo");
		AdminController.initAIData();
	}
	public static void main(String[] args) {
		System.out.println("Spring Version: " + SpringVersion.getVersion());
		SpringApplication.run(AiDashboardApplication.class, args);
	}

}
