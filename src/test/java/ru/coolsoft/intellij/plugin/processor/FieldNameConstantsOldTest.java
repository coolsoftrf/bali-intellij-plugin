package ru.coolsoft.intellij.plugin.processor;

import ru.coolsoft.intellij.plugin.AbstractLombokParsingTestCase;
import ru.coolsoft.intellij.plugin.LombokTestUtil;

/**
 * Unit tests for @FieldNameConstants annotation from old version of lombok
 */
public class FieldNameConstantsOldTest extends AbstractLombokParsingTestCase {

  public void testFieldnameconstants$FieldNameConstantsOldBasic() {
    doTest(true);
  }

  @Override
  protected void loadLombokLibrary() {
    LombokTestUtil.loadOldLombokLibrary(myFixture.getProjectDisposable(), getModule());
  }
}
