package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.actionSystem.AnAction;
import ru.coolsoft.intellij.plugin.action.LombokLightActionTestCase;

public class DelombokUtilityClassActionTest extends LombokLightActionTestCase {

  @Override
  protected AnAction getAction() {
    return new DelombokUtilityClassAction();
  }

  @Override
  protected String getBasePath() {
    return super.getBasePath() + "/action/delombok/utilityclass";
  }

  public void testUtilityClass() throws Exception {
    doTest();
  }

  public void testUtilityInner() throws Exception {
    doTest();
  }
}
