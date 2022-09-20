package ru.coolsoft.intellij.plugin.processor;

import ru.coolsoft.intellij.plugin.AbstractLombokParsingTestCase;

/**
 * Unit tests for @FieldNameConstants annotation from current version of lombok
 */
public class FieldNameConstantsTest extends AbstractLombokParsingTestCase {

  public void testFieldnameconstants$FieldNameConstantsBasic() {
    doTest(true);
  }

  public void testFieldnameconstants$FieldNameConstantsEnum() {
    doTest(true);
  }

  public void testFieldnameconstants$FieldNameConstantsHandrolled() {
    doTest(true);
  }

}
