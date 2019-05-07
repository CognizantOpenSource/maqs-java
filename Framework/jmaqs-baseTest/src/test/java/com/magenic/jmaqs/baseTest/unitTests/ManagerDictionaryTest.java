package com.magenic.jmaqs.baseTest.unitTests;

import com.magenic.jmaqs.baseTest.ManagerDictionary;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ManagerDictionaryTest {

  @Test public void testClose() {


    try(ManagerDictionary managerDictionary = new ManagerDictionary())
    {
     managerDictionary.
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test public void testGetDriver() {
  }

  @Test public void testPut() {
  }

  @Test public void testPutOrOverride() {
  }

  @Test public void testPutOrOverride1() {
  }

  @Test public void testRemove() {
  }
}