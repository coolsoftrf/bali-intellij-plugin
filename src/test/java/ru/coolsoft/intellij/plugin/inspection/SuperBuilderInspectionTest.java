package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.util.BuildNumber;

public class SuperBuilderInspectionTest extends LombokInspectionTest {

  @Override
  protected String getTestDataPath() {
    return TEST_DATA_INSPECTION_DIRECTORY + "/superbuilder";
  }

  @Override
  protected InspectionProfileEntry getInspection() {
    return new LombokInspection();
  }

  public void testBuilderDefaultValue() {
    final BuildNumber buildNumber = ApplicationInfo.getInstance().getBuild();
    if (193 <= buildNumber.getBaselineVersion()) {
      doNamedTest(getTestName(false) + "193");
    } else {
      doTest();
    }
  }

  public void testBuilderInvalidIdentifier() {
    doTest();
  }

  public void testBuilderRightType() {
    doTest();
  }

  public void testBuilderDefaultsWarnings() {
    doTest();
  }

  public void testBuilderInvalidUse() {
    doTest();
  }

  public void testBuilderInvalidInnerBuilderClass() {
    doTest();
  }

  public void testBuilderObtainVia() {
    doTest();
  }
}
