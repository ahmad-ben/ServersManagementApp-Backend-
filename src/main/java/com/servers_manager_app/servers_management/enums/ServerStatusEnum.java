package com.servers_manager_app.servers_management.enums;

public enum ServerStatusEnum {
  SERVER_UP("SERVER_UP"),
  SERVER_DOWN("SERVER_DOWN");

  private final String serverStatus;

  ServerStatusEnum(String serverStatus){
    this.serverStatus = serverStatus;
  }

  public String getServerStatus(){
    return this.serverStatus;
  }
}
