package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.actionSystem.AnAction;
import ru.coolsoft.intellij.plugin.action.LombokLightActionTestCase;

public class DelombokGetterActionTest extends LombokLightActionTestCase {

  @Override
  protected AnAction getAction() {
    return new DelombokGetterAction();
  }

  @Override
  protected String getBasePath() {
    return super.getBasePath() + "/action/delombok/getter";
  }

  public void testGetterFields() throws Exception {
    doTest();
  }

  public void testGetterClass() throws Exception {
    doTest();
  }

}
