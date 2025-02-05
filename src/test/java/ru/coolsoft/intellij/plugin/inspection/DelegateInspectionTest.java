package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.InspectionProfileEntry;

public class DelegateInspectionTest extends LombokInspectionTest {

  @Override
  protected String getTestDataPath() {
    return TEST_DATA_INSPECTION_DIRECTORY + "/delegate";
  }

  @Override
  protected InspectionProfileEntry getInspection() {
    return new LombokInspection();
  }

  public void testConcreteType() {
    doTest();
  }

  public void testOnMethodWithParameter() {
    doTest();
  }

  public void testOnStaticFieldOrMethod() {
    doTest();
  }

  public void testRecursionType() {
    doTest();
  }

}
