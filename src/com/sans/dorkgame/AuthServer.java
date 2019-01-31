package com.sans.dorkgame;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AuthServer extends Server {
  public HashMap<String, String> cookies = new LinkedHashMap<>();

  public AuthServer(String ip, String port) {
    super(ip, port);
  }
  public AuthServer(String ip) {
    super(ip, "50");
  }

  public BufferedInputStream login() throws IOException, FailedAuthException {
    BufferedInputStream is = new BufferedInputStream(new URL("www.liamiam.com/dorkgame/auth/" + DorkGame.username + ".pswd").openStream());
    String contents = new String(is.readAllBytes());
    String[] workable = contents.split("; ");
    for (String s : workable) {
      String[] workable2 = s.split(" = ");
      cookies.put(workable2[0], workable2[1]);
    }
    if(
            cookies.get("username").equals(DorkGame.username) &&
            cookies.get("password").equals(new String(DorkGame.currentPlayer.password))
      ) {
      DorkGame.log.println("Successful login was made");
    } else throw new FailedAuthException();
    return is;
  }
}
