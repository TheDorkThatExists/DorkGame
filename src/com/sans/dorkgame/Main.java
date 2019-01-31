package com.sans.dorkgame;

import java.awt.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Base64;
import java.util.Objects;

import static com.sans.dorkgame.DorkGame.*;

public class Main {
  private Thread beginMain = new Thread(() -> {
    textThread = new Thread(() -> {
      while(true) {
        new Thread(() -> {
          byte[] buf = new byte[256];
          DatagramPacket packet = new DatagramPacket(buf, buf.length);
          try {
            socket.receive(packet);
          } catch (IOException e) {
            e.printStackTrace();
          }
          InetAddress address = packet.getAddress();
          int port = packet.getPort();
          packet = new DatagramPacket(buf, buf.length, address, port);
          String received = new String(packet.getData(), 0, packet.getLength());
          String[] data = received.split(";");
          if (data.length == 1) System.out.println(data[0]);
          else if (data.length == 2) System.out.println('[' + data[0] + "] " + data[1]);
          else throw new HeadlessException("Invalid chat message sent");
        }).start();
        new Thread(() -> {
          if (enterButton && !commands.isRelated(chatbuf.split(" ")[0])) {
            byte[] buf = chatbuf.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
              socket.send(packet);
            } catch (IOException e) {
              e.printStackTrace();
            }
            chatbuf = "";
            enterButton = false;
          }
        }).start();
      }
    });
    emoteThread = new Thread(() -> {
      emotes = new Emote[] {
              new Emote() { // /emote 0 <target>
                @Override
                public void init() {
                  text = "* &UNAME just hugged &TARGET";
                  abvr = new String[] {
                          "&UNAME -> " + username,
                          "&TARGET -> " + chatbuf.split(" ")[2]
                  };
                }
              },
              new Emote() { // /emote 1
                @Override
                public void init() {
                  text = "* &UNAME wants to commit suicide";
                  abvr = new String[] {
                          "&UNAME -> " + username,
                  };
                }
              },
              new Emote() { // /emote 2 <target> <action>
                @Override
                public void init() {
                  text = "* &UNAME wants &TARGET to &ACTION";
                  int lolz = 0;
                  for (int i = 0; i < chatbuf.length(); i++) {
                    lolz += chatbuf.split(" ")[i > 2 ? 3:i].length() + 1;
                    if(i > 2) break;
                  }
                  abvr = new String[] {
                          "&UNAME -> " + username,
                          "&TARGET -> " + chatbuf.split(" ")[2],
                          "&ACTION -> " + chatbuf.substring(lolz),
                  };
                }
              }
      };

      while(true) {
        if(enterButton)
        if(commands.isRelated(chatbuf.split(" ")[0]))
        if(Objects.equals(chatbuf.split(" ")[0], "/emote")) {
          emotes[Integer.parseInt(chatbuf.split(" ")[1])].init();
          emotes[Integer.parseInt(chatbuf.split(" ")[1])].run();
        }
      }
    });
    multiplayerThread = new Thread(() -> {
      new Thread(() -> { // Login thread
        if(new File("testaccount.yaml").exists()){
          System.err.println("Using Test account... " +
                             "Please follow the rules at www.liamiam.com/dorkgame/dev/tester/rules.txt");
          try {
            authFile = new BufferedReader(new FileReader("testaccount.yaml"));
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          setAuthFromFile();
        } else if(new File("auth.yaml").exists()) {
          System.out.println("Using preset account...");
          try {
            authFile = new BufferedReader(new FileReader("auth.yaml"));
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          setAuthFromFile();
        } else {
          System.err.println("Failed to find preset auth file...");
          username = "UnauthorizedUser";
          currentPlayer.username = username;
          currentPlayer.password = Base64.getEncoder().encode("unauth".getBytes());
        }

      }).start();
      new Thread(() -> { // Public server actions
        // This is not implemented yet
      }).start();
    });
  });

  public static void main(String[] args) {
    new Main().beginMain.start();
  }
}
