package ru.coolsoft.intellij.plugin.handler;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.util.PsiTreeUtil;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationSearchUtil;


public final class BuilderHandler {

  public static boolean isDefaultBuilderValue(@NotNull PsiElement highlightedElement) {
    PsiField field = PsiTreeUtil.getParentOfType(highlightedElement, PsiField.class);
    if (field == null) {
      return false;
    }

    return PsiAnnotationSearchUtil.isAnnotatedWith(field, LombokClassNames.BUILDER_DEFAULT);
  }
}
