package ru.coolsoft.intellij.plugin.processor;

import ru.coolsoft.intellij.plugin.AbstractLombokParsingTestCase;

/**
 * Unit tests for IntelliJPlugin for Lombok, based on lombok test classes
 */
public class IntelliJLombokPluginTest extends AbstractLombokParsingTestCase {

  @Override
  protected boolean shouldCompareCodeBlocks() {
    return false;
  }

  public void testNonNullPlain() {
    doTest();
  }

  public void testSynchronizedName() {
    doTest();
  }

  public void ignore_testSynchronizedPlain() {
    //TODO known problem, try to fix later
    doTest();
  }

}
