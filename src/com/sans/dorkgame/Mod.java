package com.sans.dorkgame;

public abstract class Mod {
  public static void addMod(Mod mod) {
    DorkGame.mods.add(mod);
  }

  public abstract void preinit();
  public abstract void init();

}
