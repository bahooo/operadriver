package com.opera.core.systems;

import junit.framework.Assert;

import org.junit.Test;

public class ElementsTest extends TestBase {
  @Test
  public void testClear() {
    driver.get(fixture("javascript.html"));
    driver.mouseEvent(1, 1, 1);

    driver.findElementById("input_email").clear();
    Assert.assertTrue(driver.findElementById("log").getValue().contains(
        "email changed"));
  }

  @Test
  public void testRightClick() {
    driver.get(fixture("mouse.html"));
    driver.mouseEvent(1, 1, 1);

    // Cast as OperaWebElement to make rightClick available
    ((OperaWebElement) driver.findElementById("test")).rightClick();
    Assert.assertTrue(driver.findElementById("log").getValue().contains(
        "mousedown 2"));
    Assert.assertTrue(driver.findElementById("log").getValue().contains(
        "mouseup 2"));
  }

  @Test
  public void testHover() {
    driver.get(fixture("mouse.html"));
    driver.mouseEvent(1, 1, 1);

    OperaWebElement test = ((OperaWebElement) driver.findElementById("test"));
    String hash = test.getImageHash();
    test.hover();
    Assert.assertNotSame(hash, test.getImageHash());
  }
}
