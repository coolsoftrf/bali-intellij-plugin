package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.siyeh.ig.resources.AutoCloseableResourceInspection;

public class AutoCloseableResourceInspectionTest extends LombokInspectionTest {

  @Override
  protected String getTestDataPath() {
    return TEST_DATA_INSPECTION_DIRECTORY + "/autoCloseableResource";
  }

  @Override
  protected InspectionProfileEntry getInspection() {
    return new AutoCloseableResourceInspection();
  }

  public void testAutoCloseableCleanup() {
    doTest();
  }

}
