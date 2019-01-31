package com.sans.dorkgame;

import java.io.*;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

public class DorkGame {
  public static Thread
          textThread, multiplayerThread, emoteThread,
          renderThread;
  public static DatagramSocket socket;
  public static String         chatbuf;
  public static boolean        enterButton;
  public static boolean        admin = false;
  public static StringArray    commands = new StringArray(new String[] {
          "/emote", "/leave", "/admin", "/deadmin", "/ban", "/unban", "/tmpban"
  });
  public static Emote[]        emotes;
  public static String         username;
  public static int            spawnPointX, spawnPointY;
  public static Player         currentPlayer;
  public static List<Player>   otherPlayers = new LinkedList<>();
  public static BufferedReader authFile;
  public static PrintStream    log;
  public static List<Mod>      mods = new LinkedList<>();
  public static AuthServer     authServer = new AuthServer("www.liamiam.com/dorkgame/auth/upsk.pswd");

  static {
    try {
      log = new PrintStream(new File("dorkgame.log"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      socket = new DatagramSocket(4634);
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }

  public static void endGame() {
    textThread.interrupt();
    multiplayerThread.interrupt();
    emoteThread.interrupt();
    renderThread.interrupt();
  }

  public static void setAuthFromFile() {
    try {
      username = authFile.readLine().split("username: ")[0];
      currentPlayer.username = username;
      currentPlayer.password = Base64.getEncoder().encode(authFile.readLine().split("password: ")[0].getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
