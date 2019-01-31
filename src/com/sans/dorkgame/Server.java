package com.sans.dorkgame;

public abstract class Server implements IPAddress {
  protected String ip;
  protected String port;

  public Server(String ip, String port) {
    this.ip = ip;
    this.port = port;
  }

  public Server(String ip) {
    this(ip, "20");
  }

  @Override
  public String getIP() {
    return ip;
  }

  @Override
  public String getPort() {
    return port;
  }
}
