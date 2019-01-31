package com.sans.dorkgame;

import java.util.Objects;

public class StringArray {
  private String[] array;

  public StringArray(String[] array) {
    this.array = array;
  }

  public boolean isRelated(String a) {
    for (int i = 0; i < array.length; i++) {
      if(Objects.equals(a, array[i])) return true;
    }
    return false;
  }

  public boolean isRelatedButOne(String a, String b) {
    for (int i = 0; i < array.length; i++) {
      if(Objects.equals(a, array[i]) && !Objects.equals(a, b)) return true;
    }
    return false;
  }
}
