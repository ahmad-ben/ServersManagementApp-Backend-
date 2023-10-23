package com.servers_manager_app.servers_management.models;

import static jakarta.persistence.GenerationType.AUTO;

import com.servers_manager_app.servers_management.enums.ServerStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
  @Id 
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @Column(unique = true)
  @NotEmpty(message = "IP cannot be empty or null.")
  private String ipAddress;

  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private ServerStatusEnum serverStatus;

}
