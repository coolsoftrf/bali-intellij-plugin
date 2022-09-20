package ru.coolsoft.intellij.plugin.processor;

import ru.coolsoft.intellij.plugin.AbstractLombokParsingTestCase;

/**
 * Unit tests for IntelliJPlugin for Lombok, based on lombok test classes
 */
public class ToStringTest extends AbstractLombokParsingTestCase {

  public void testTostring$ToStringAutoExclude() {
    doTest(true);
  }

  public void testTostring$ToStringExplicitEmptyOf() {
    doTest(true);
  }

  public void testTostring$ToStringExplicitOfAndExclude() {
    doTest(true);
  }

  public void testTostring$ToStringExplicitInclude() {
    doTest(true);
  }

  public void testTostring$ToStringInner() {
    doTest(true);
  }

  public void testTostring$ToStringNewStyle() {
    doTest(true);
  }

  public void testTostring$ToStringPlain() {
    doTest(true);
  }

  public void testTostring$ToStringSimpleClassName() {
    doTest(true);
  }

  public void testTostring$ToStringWithNamedExistingMethods() {
    doTest(true);
  }
}
