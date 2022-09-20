package ru.coolsoft.intellij.plugin.processor;

import ru.coolsoft.intellij.plugin.AbstractLombokParsingTestCase;

/**
 * Unit tests for IntelliJPlugin for Lombok, based on lombok test classes
 */
public class SetterTest extends AbstractLombokParsingTestCase {

  public void testSetter$SetterAccessLevel() {
    doTest(true);
  }

  public void testSetter$SetterAlreadyExists() {
    doTest(true);
  }

  public void testSetter$SetterDeprecated() {
    doTest(true);
  }

  public void testSetter$SetterOnClass() {
    doTest(true);
  }

  public void testSetter$SetterOnMethodOnParam() {
    doTest(true);
  }

  public void testSetter$SetterOnStatic() {
    doTest(true);
  }

  public void testSetter$SetterPlain() {
    doTest(true);
  }

  public void testSetter$SetterWithDollar() {
    doTest(true);
  }
}
