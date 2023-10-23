package com.servers_manager_app.servers_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.servers_manager_app.servers_management.enums.ServerStatusEnum;
import com.servers_manager_app.servers_management.models.Server;
import com.servers_manager_app.servers_management.repo.ServerRepoInterface;

@SpringBootApplication
public class ServersManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServersManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepoInterface serverRepoInterface){
		return args -> {
			serverRepoInterface.save(
				new Server(
					null, 
					"192.168.1.1", 
					"Router", 
					"4GB", 
					"huawei", 
					"http://localhost:8080/server/image/server1.png", 
					ServerStatusEnum.SERVER_DOWN
				)
			);
			serverRepoInterface.save(
				new Server(
					null, 
					"192.168.1.4", 
					"My PC", 
					"4GB", 
					"HP EliteBook", 
					"http://localhost:8080/server/image/server2.png", 
					ServerStatusEnum.SERVER_UP
				)
			);
		};
	}

}
