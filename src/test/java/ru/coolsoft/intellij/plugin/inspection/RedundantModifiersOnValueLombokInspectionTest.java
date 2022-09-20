package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.InspectionProfileEntry;
import ru.coolsoft.intellij.plugin.inspection.modifiers.RedundantModifiersOnValueLombokAnnotationInspection;

public class RedundantModifiersOnValueLombokInspectionTest extends LombokInspectionTest {

  @Override
  protected String getTestDataPath() {
    return TEST_DATA_INSPECTION_DIRECTORY + "/redundantModifierInspection";
  }

  @Override
  protected InspectionProfileEntry getInspection() {
    return new RedundantModifiersOnValueLombokAnnotationInspection();
  }

  public void testValueClass() {
    doTest();
  }

}
