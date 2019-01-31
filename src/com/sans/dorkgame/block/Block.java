package com.sans.dorkgame.block;

import com.sans.dorkgame.GameRunnable;
import com.sans.dorkgame.block.behavior.Behavior;

public abstract class Block {
  private Behavior behavior;

  public abstract Behavior getBehavior();
  public abstract GameRunnable adjasentRunCommand(String command);
  public abstract char getChar();
  public abstract void init();
}
