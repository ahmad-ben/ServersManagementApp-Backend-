package com.servers_manager_app.servers_management.services.implementations;

import static com.servers_manager_app.servers_management.enums.ServerStatusEnum.SERVER_DOWN;
import static com.servers_manager_app.servers_management.enums.ServerStatusEnum.SERVER_UP;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.servers_manager_app.servers_management.models.Server;
import com.servers_manager_app.servers_management.repo.ServerRepoInterface;
import com.servers_manager_app.servers_management.services.ServerServicesInterface;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerServicesInterface {
  private final ServerRepoInterface serverRepoInterface;

  @Override
  public Server create(Server server) {
    log.info("Saving new server: {}", server.getName());
    server.setImageUrl(setServerImageUrl());
    return serverRepoInterface.save(server);
  }
  
  @Override
  public Server ping(String ipAddress) throws IOException {
    log.info("Pinging on the server: {}", ipAddress);
    Server server = serverRepoInterface.findByIpAddress(ipAddress);
    InetAddress address = InetAddress.getByName(ipAddress);
    server.setServerStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
    serverRepoInterface.save(server);
    return server;
  }

  @Override
  public Collection<Server> list(int limit) {
    log.info("Fetching all servers.");
    return serverRepoInterface.findAll(PageRequest.of(0, limit)).toList();
  }
  
  @Override
  public Server get(Long id) {
    log.info("Fetching server by ID: {}.", id);
    return serverRepoInterface.findById(id).get();
  }

  @Override
  public Server update(Server server) {
    log.info("Updating server: {}.", server.getName());
    return serverRepoInterface.save(server);
  }

  @Override
  public Boolean delete(Long id) {
    log.info("Delete a server by its ID: {}.", id);
    serverRepoInterface.deleteById(id);
    return true;
  }

  String setServerImageUrl(){
    String[] imagesNames = { "server1.png", "server2.png", "server3.png", "server4.png"};
    return ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/server/image/" + imagesNames[new Random().nextInt(4)])
        .toUriString();
  }
}
