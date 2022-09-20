package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.actionSystem.AnAction;
import ru.coolsoft.intellij.plugin.action.LombokLightActionTestCase;

public class DelombokDelegateActionTest extends LombokLightActionTestCase {

  @Override
  protected AnAction getAction() {
    return new DelombokDelegateAction();
  }

  @Override
  protected String getBasePath() {
    return super.getBasePath() + "/action/delombok/delegate";
  }

  public void testDelegateOnField() throws Exception {
    doTest();
  }

  public void testDelegateOnMethod() throws Exception {
    doTest();
  }

}
