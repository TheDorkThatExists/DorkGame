package com.sans.dorkgame.block;

import com.sans.dorkgame.GameRunnable;
import com.sans.dorkgame.block.behavior.Behavior;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BlockAir extends Block {
  private HashMap<String, GameRunnable> commands = new LinkedHashMap<>();

  @Override
  public Behavior getBehavior() {
    return null;
  }

  @Override
  public GameRunnable adjasentRunCommand(String command) {
    return commands.get(command);
  }

  @Override
  public char getChar() {
    return ' ';
  }

  @Override
  public void init() {
    commands.put("hit", new GameRunnable() {
      @Override
      public void run() {

      }
    });
  }
}
