package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.codeInspection.dataFlow.DataFlowInspection;


public class DataFlowWithDisabledCachingInspectionTest extends LombokInspectionTest {

  @Override
  protected String getTestDataPath() {
    return TEST_DATA_INSPECTION_DIRECTORY + "/diverse";
  }

  @Override
  protected InspectionProfileEntry getInspection() {
    return new DataFlowInspection();
  }

  public void testDefaultBuilderFinalValueInspectionIsAlwaysThat() {
    doTest();
  }
}
