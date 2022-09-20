package ru.coolsoft.intellij.plugin.intention.valvar.to;

import static com.intellij.psi.PsiModifier.FINAL;

import com.intellij.psi.*;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.LombokClassNames;

public class ReplaceExplicitTypeWithVarIntentionAction extends AbstractReplaceExplicitTypeWithVariableIntentionAction {

  public ReplaceExplicitTypeWithVarIntentionAction() {
    super(LombokClassNames.VAR);
  }

  @Override
  protected boolean isAvailableOnDeclarationCustom(@NotNull PsiDeclarationStatement declarationStatement, @NotNull PsiLocalVariable localVariable) {
    return isNotFinal(localVariable);
  }

  @Override
  protected void executeAfterReplacing(PsiVariable psiVariable) {
  }

  @Override
  public boolean isAvailableOnVariable(@NotNull PsiVariable psiVariable) {
    if (!(psiVariable instanceof PsiParameter)) {
      return false;
    }
    PsiParameter psiParameter = (PsiParameter) psiVariable;
    PsiElement declarationScope = psiParameter.getDeclarationScope();
    if (!(declarationScope instanceof PsiForStatement) && !(declarationScope instanceof PsiForeachStatement)) {
      return false;
    }
    PsiTypeElement typeElement = psiParameter.getTypeElement();
    return typeElement != null && !typeElement.isInferredType() && isNotFinal(psiParameter);
  }

  private boolean isNotFinal(@NotNull PsiVariable variable) {
    PsiModifierList modifierList = variable.getModifierList();
    return modifierList == null || !modifierList.hasExplicitModifier(FINAL);
  }
}
