package ru.coolsoft.intellij.plugin.processor.modifier;

import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.problem.ProblemNewBuilder;
import ru.coolsoft.intellij.plugin.processor.clazz.UtilityClassProcessor;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationSearchUtil;

/**
 * @author Florian Böhm
 */
public class UtilityClassModifierProcessor implements ModifierProcessor {

  public static boolean isModifierListSupported(@NotNull PsiModifierList modifierList) {
    PsiElement modifierListParent = modifierList.getParent();

    if (modifierListParent instanceof PsiClass) {
      PsiClass parentClass = (PsiClass) modifierListParent;
      if (PsiAnnotationSearchUtil.isAnnotatedWith(parentClass, LombokClassNames.UTILITY_CLASS)) {
        return UtilityClassProcessor.validateOnRightType(parentClass, new ProblemNewBuilder());
      }
    }

    if (!isElementFieldOrMethodOrInnerClass(modifierListParent)) {
      return false;
    }

    PsiClass searchableClass = PsiTreeUtil.getParentOfType(modifierListParent, PsiClass.class, true);

    return null != searchableClass && PsiAnnotationSearchUtil.isAnnotatedWith(searchableClass, LombokClassNames.UTILITY_CLASS) && UtilityClassProcessor.validateOnRightType(searchableClass, new ProblemNewBuilder());
  }

  @Override
  public boolean isSupported(@NotNull PsiModifierList modifierList) {
    return isModifierListSupported(modifierList);
  }

  @Override
  public void transformModifiers(@NotNull PsiModifierList modifierList, @NotNull final Set<String> modifiers) {
    final PsiElement parent = modifierList.getParent();

    // FINAL
    if (parent instanceof PsiClass) {
      PsiClass psiClass = (PsiClass) parent;
      if (PsiAnnotationSearchUtil.isAnnotatedWith(psiClass, LombokClassNames.UTILITY_CLASS)) {
        modifiers.add(PsiModifier.FINAL);
      }
    }

    // STATIC
    if (isElementFieldOrMethodOrInnerClass(parent)) {
      modifiers.add(PsiModifier.STATIC);
    }
  }

  private static boolean isElementFieldOrMethodOrInnerClass(PsiElement element) {
    return element instanceof PsiField || element instanceof PsiMethod ||
      (element instanceof PsiClass && element.getParent() instanceof PsiClass && !((PsiClass) element.getParent()).isInterface());
  }
}
