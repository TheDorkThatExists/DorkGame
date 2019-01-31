package com.sans.dorkgame;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

class OpenConnection extends Connection {
  private boolean isOpen = true;

  public OpenConnection(BufferedInputStream is) {
    super(is);
  }

  OpenConnection(BufferedInputStream is, Object[] buffer) {
    super(is, buffer);
  }

  @Override
  public String read() throws IOException {
    return Arrays.toString(is.readAllBytes());
  }

  @Override
  public String read(int len) throws IOException {
    return Arrays.toString(is.readNBytes(len));
  }

  public void close() {
    assert isOpen;
    isOpen = false;
  }

  public ClosedConnection migrate() {
    assert !isOpen;
    return new ClosedConnection(is, buffer);
  }
}

class ClosedConnection extends Connection {
  public ClosedConnection(BufferedInputStream is) {
    super(is);
  }

  ClosedConnection(BufferedInputStream is, Object[] buffer) {
    super(is, buffer);
  }

  @Override
  public String read() throws ClosedConnectionException {
    throw new ClosedConnectionException();
  }

  @Override
  public String read(int len) throws ClosedConnectionException {
    throw new ClosedConnectionException();
  }

  public OpenConnection reopen() { return new OpenConnection(is, buffer); }

}

class EncryptedConnection extends Connection {
  public EncryptedConnection(BufferedInputStream is) {
    super(is);
  }

  EncryptedConnection(BufferedInputStream is, Object[] buffer) {
    super(is, buffer);
  }

  @Override
  public String read() throws IOException {
    return Arrays.toString(Base64.getDecoder().decode(is.readAllBytes()));
  }

  @Override
  public String read(int len) throws Exception {
    throw new Exception("NOT AVAILABLE");
  }
}

class ClosedConnectionException extends Exception {
  public ClosedConnectionException() {
  }

  public ClosedConnectionException(String message) {
    super(message);
  }

  public ClosedConnectionException(String message, Throwable cause) {
    super(message, cause);
  }

  public ClosedConnectionException(Throwable cause) {
    super(cause);
  }
}

abstract class Connection {
  protected BufferedInputStream is;
  protected Object[] buffer;

  public Connection(BufferedInputStream is) {
    this.is = is;
    buffer = new Object[512];
  }

  Connection(BufferedInputStream is, Object[] buffer) {
    this.buffer = buffer;
    this.is = is;
  }

  public abstract String read() throws IOException, ClosedConnectionException;
  public abstract String read(int len) throws Exception;
}