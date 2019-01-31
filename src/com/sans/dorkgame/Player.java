package com.sans.dorkgame;

public class Player {
  public int x, y;
  public String username;
  public byte[] password; // triple encrypted

  public Player() {
    x = DorkGame.spawnPointX;
    y = DorkGame.spawnPointY;
  }

  public Player(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
