package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.InspectionProfileEntry;

public class FieldDefaultsOnEnumInspectionTest extends LombokInspectionTest {

  @Override
  protected String getTestDataPath() {
    return TEST_DATA_INSPECTION_DIRECTORY + "/fielddefaults";
  }

  @Override
  protected InspectionProfileEntry getInspection() {
    return null;
  }

  public void testEnumClass() {
    doTest();
  }
}
