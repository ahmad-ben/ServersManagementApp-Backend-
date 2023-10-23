package com.servers_manager_app.servers_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servers_manager_app.servers_management.models.Server;


public interface ServerRepoInterface extends JpaRepository<Server, Long> { 
  Server findByIpAddress(String ipAddress);
}
