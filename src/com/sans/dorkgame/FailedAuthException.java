package com.sans.dorkgame;

import java.awt.*;

public class FailedAuthException extends HeadlessException {
  public FailedAuthException() {
    super("Authentication failed");
  }

  public FailedAuthException(String msg) {
    super(msg);
  }
}
