package ru.coolsoft.intellij.plugin.handler;

import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.siyeh.ig.psiutils.InitializationUtils;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationSearchUtil;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationUtil;

public final class LazyGetterHandler {

  public static boolean isLazyGetterHandled(@NotNull PsiElement element) {
    if (!(element instanceof PsiIdentifier)) {
      return false;
    }
    PsiField field = PsiTreeUtil.getParentOfType(element, PsiField.class);
    if (field == null) {
      return false;
    }

    final PsiAnnotation getterAnnotation = PsiAnnotationSearchUtil.findAnnotation(field, LombokClassNames.GETTER);
    return null != getterAnnotation && PsiAnnotationUtil.getBooleanAnnotationValue(getterAnnotation, "lazy", false);
  }

  public static boolean isInitializedInConstructors(@NotNull PsiElement element) {
    if (!(element instanceof PsiIdentifier)) {
      return false;
    }
    PsiElement parent = element.getParent();
    if (!(parent instanceof PsiReferenceExpression)) {
      return false;
    }
    PsiElement qualifier = ((PsiReferenceExpression) parent).getQualifier();
    if (qualifier == null) {
      return false;
    }
    PsiReference reference = qualifier.getReference();
    if (reference == null) {
      return false;
    }
    PsiElement field = reference.resolve();
    if (!(field instanceof PsiField)) {
      return false;
    }
    PsiClass containingClass = ((PsiField) field).getContainingClass();
    if (containingClass == null) {
      return false;
    }
    return isInitializedInConstructors((PsiField) field, containingClass);
  }

  private static boolean isInitializedInConstructors(@NotNull PsiField field, @NotNull PsiClass aClass) {
    final PsiMethod[] constructors = aClass.getConstructors();
    if (constructors.length == 0) {
      return false;
    }

    for (final PsiMethod constructor : constructors) {
      if (!InitializationUtils.methodAssignsVariableOrFails(constructor, field)) {
        return false;
      }
    }
    return true;
  }
}
