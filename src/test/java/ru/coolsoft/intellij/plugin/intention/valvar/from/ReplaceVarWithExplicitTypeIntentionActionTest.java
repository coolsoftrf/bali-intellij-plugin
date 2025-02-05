package ru.coolsoft.intellij.plugin.intention.valvar.from;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.util.PsiTreeUtil;
import ru.coolsoft.intellij.plugin.intention.LombokIntentionActionTest;

public class ReplaceVarWithExplicitTypeIntentionActionTest extends LombokIntentionActionTest {

  @Override
  protected String getBasePath() {
    return ReplaceValWithExplicitTypeGenericsIntentionActionTest.REPLACE_VAL_VAR_WITH_EXPLICIT_TYPE_DIRECTORY;
  }

  @Override
  public IntentionAction getIntentionAction() {
    return new ReplaceVarWithExplicitTypeIntentionAction();
  }

  @Override
  public boolean wasInvocationSuccessful() {
    PsiElement elementAtCaret = myFixture.getFile().findElementAt(myFixture.getCaretOffset());
    PsiLocalVariable localVariable = PsiTreeUtil.getParentOfType(elementAtCaret, PsiLocalVariable.class);
    if (localVariable == null) {
      return false;
    }
    PsiTypeElement typeElement = localVariable.getTypeElement();
    return typeElement.getText().equals("String");
  }

  public void testReplaceVar() {
    doTest();
  }
}
