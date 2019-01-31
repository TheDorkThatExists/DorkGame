package com.sans.dorkgame;

import java.util.HashMap;
import java.util.Objects;

public abstract class Emote implements Runnable {
  protected String   text;
  protected String[] abvr;

  public void run() {
    Objects.requireNonNull(text, "Emote invalid");
    String lolz = text;

    for (String s : abvr) {
      String[] s0 = s.split(" -> ");
      lolz = lolz.replaceAll(s0[0], s0[1]);
    }

    System.out.println(lolz);
  }

  public abstract void init();
}
