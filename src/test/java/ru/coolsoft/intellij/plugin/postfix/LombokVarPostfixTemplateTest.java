package ru.coolsoft.intellij.plugin.postfix;

import ru.coolsoft.intellij.plugin.AbstractLombokLightCodeInsightTestCase;

public class LombokVarPostfixTemplateTest extends AbstractLombokLightCodeInsightTestCase {

  public void testSimpleVarl() {
    doTest("/postfix/varl/");
  }

  public void testSimpleVal() {
    doTest("/postfix/val/");
  }

  protected void doTest(String pathSuffix) {
    myFixture.configureByFile(getBasePath() + pathSuffix + getTestName(true) + ".java");
    myFixture.type('\t');
    myFixture.checkResultByFile(getBasePath() + pathSuffix + getTestName(true) + "_after.java", true);
  }
}
