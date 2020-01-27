/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.base;

public class FakeTestClass {

  private String fakeString;
  private int fakeInt;
  private double fakeDouble;
  public FakeTestClass() {
  }

  public FakeTestClass(String fakeString, int fakeInt, double fakeDouble) {
    this.fakeString = fakeString;
    this.fakeInt = fakeInt;
    this.fakeDouble = fakeDouble;
  }

  /**
   * Getter for property 'fakeString'.
   *
   * @return Value for property 'fakeString'.
   */
  public String getFakeString() {
    return fakeString;
  }

  /**
   * Setter for property 'fakeString'.
   *
   * @param fakeString Value to set for property 'fakeString'.
   */
  public void setFakeString(String fakeString) {
    this.fakeString = fakeString;
  }

  /**
   * Getter for property 'fakeInt'.
   *
   * @return Value for property 'fakeInt'.
   */
  public int getFakeInt() {
    return fakeInt;
  }

  /**
   * Setter for property 'fakeInt'.
   *
   * @param fakeInt Value to set for property 'fakeInt'.
   */
  public void setFakeInt(int fakeInt) {
    this.fakeInt = fakeInt;
  }

  /**
   * Getter for property 'fakeDouble'.
   *
   * @return Value for property 'fakeDouble'.
   */
  public double getFakeDouble() {
    return fakeDouble;
  }

  /**
   * Setter for property 'fakeDouble'.
   *
   * @param fakeDouble Value to set for property 'fakeDouble'.
   */
  public void setFakeDouble(double fakeDouble) {
    this.fakeDouble = fakeDouble;
  }
}
