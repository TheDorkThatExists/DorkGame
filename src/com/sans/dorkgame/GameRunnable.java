package com.sans.dorkgame;

public abstract class GameRunnable implements Runnable {
  protected void setUsername(String newName) {
    DorkGame.username = newName;
    DorkGame.currentPlayer.username = newName;
  }
}
