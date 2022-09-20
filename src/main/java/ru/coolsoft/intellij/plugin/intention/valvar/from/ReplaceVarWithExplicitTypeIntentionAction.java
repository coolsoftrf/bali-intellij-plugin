package ru.coolsoft.intellij.plugin.intention.valvar.from;

import com.intellij.psi.PsiVariable;

import ru.coolsoft.intellij.plugin.LombokClassNames;

public class ReplaceVarWithExplicitTypeIntentionAction extends AbstractReplaceVariableWithExplicitTypeIntentionAction {

  public ReplaceVarWithExplicitTypeIntentionAction() {
    super(LombokClassNames.VAR);
  }

  @Override
  protected void executeAfterReplacing(PsiVariable psiVariable) {

  }
}
