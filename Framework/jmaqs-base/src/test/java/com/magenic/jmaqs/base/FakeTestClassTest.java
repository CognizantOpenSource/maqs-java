/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.base;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FakeTestClassTest extends BaseGenericTest {

  @Test
  public void testConstructor()
  {
    FakeTestClass fakeTestClass = new FakeTestClass();
    Assert.assertNotNull(fakeTestClass);
  }

  @Test
  public void testConstructorOverload()
  {
    FakeTestClass fakeTestClass = new FakeTestClass("Fake", 1, 1.1);
    Assert.assertNotNull(fakeTestClass);
  }

  @Test
  public void testGetFakeString() {
    FakeTestClass fakeTestClass = new FakeTestClass("Fake", 1, 1.1);
    Assert.assertNotNull(fakeTestClass);
    Assert.assertEquals(fakeTestClass.getFakeString(), "Fake");
  }
}