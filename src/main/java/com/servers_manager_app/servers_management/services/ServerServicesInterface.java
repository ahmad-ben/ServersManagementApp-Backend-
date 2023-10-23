package com.servers_manager_app.servers_management.services;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

import com.servers_manager_app.servers_management.models.Server;

public interface ServerServicesInterface {
  Server create(Server server);
  Server ping(String ipAddress) throws UnknownHostException, IOException;
  Collection<Server> list(int limit);
  Server get(Long id);
  Server update(Server server);
  Boolean delete(Long id);
}
