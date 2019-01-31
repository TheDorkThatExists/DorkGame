package com.sans.dorkgame;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MapServer extends Server {
  public MapServer(String ip, String port) {
    super(ip, port);
  }

  public MapServer(String ip) {
    super(ip);
  }

  public File getMap() throws IOException {
    Runtime.getRuntime().exec("wget " + ip + (Objects.equals(port, "nil") ? "":':' + port) + "/dorkgame.map");
    return new File("dorkgame.map");
  }

  public File getMap(int num) throws IOException {
    Runtime.getRuntime().exec("wget -q " + ip + (Objects.equals(port, "nil") ? "":':' + port) + "/dorkgame" + num + ".map");
    return new File("dorkgame " + num + ".map");
  }
}
