package com.opera.core.systems;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PreferencesDialogTest extends DesktopTestBase {
  @Test
  public void testPreferencesDialog() {
    driver.waitStart();
    driver.operaDesktopAction("Show preferences", 0, null, null);

    driver.waitForWindowShown("New Preferences Dialog");
    QuickWidget qw = driver.findWidgetByName(-1, "Startup_mode_dropdown");
    // assertTrue(qw != null);
    assertTrue("Drop down is visible", qw.isVisible());

    assertTrue("Chosen entry is startup with homepage", qw
        .verifyText("D_STARTUP_WITH_HOMEPAGE"));

    assertTrue("Startup w/home is selected", qw
        .isSelected("D_STARTUP_WITH_HOMEPAGE"));

    driver.waitStart();

    driver.operaDesktopAction("Cancel", 0, null, null);
    driver.waitForWindowClose("New Preferences Dialog");
  }
}
