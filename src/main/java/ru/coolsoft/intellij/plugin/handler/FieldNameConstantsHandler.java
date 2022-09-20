package ru.coolsoft.intellij.plugin.handler;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.util.PsiTreeUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationSearchUtil;

public final class FieldNameConstantsHandler {

  public static boolean isFiledNameConstants(@NotNull PsiElement element) {
    @Nullable PsiReferenceExpression psiReferenceExpression = PsiTreeUtil.getParentOfType(element, PsiReferenceExpression.class);
    if (psiReferenceExpression == null) {
      return false;
    }
    PsiElement psiElement = psiReferenceExpression.resolve();
    if (!(psiElement instanceof PsiModifierListOwner)) {
      return false;
    }
    return PsiAnnotationSearchUtil.isAnnotatedWith((PsiModifierListOwner) psiElement, LombokClassNames.FIELD_NAME_CONSTANTS);
  }
}
