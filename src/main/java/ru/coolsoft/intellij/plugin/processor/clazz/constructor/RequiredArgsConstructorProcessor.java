package ru.coolsoft.intellij.plugin.processor.clazz.constructor;

import com.intellij.psi.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.problem.ProblemBuilder;
import ru.coolsoft.intellij.plugin.processor.LombokPsiElementUsage;
import ru.coolsoft.intellij.plugin.util.LombokProcessorUtil;
import ru.coolsoft.intellij.plugin.util.PsiClassUtil;

/**
 * @author Plushnikov Michail
 */
public class RequiredArgsConstructorProcessor extends AbstractConstructorClassProcessor {

  public RequiredArgsConstructorProcessor() {
    super(LombokClassNames.REQUIRED_ARGS_CONSTRUCTOR, PsiMethod.class);
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
    boolean result;

    result = super.validate(psiAnnotation, psiClass, builder);

    final Collection<PsiField> allReqFields = getRequiredFields(psiClass);
    final String staticConstructorName = getStaticConstructorName(psiAnnotation);
    result &= validateIsConstructorNotDefined(psiClass, staticConstructorName, allReqFields, builder);

    return result;
  }

  @Override
  protected void generatePsiElements(@NotNull PsiClass psiClass, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final String methodVisibility = LombokProcessorUtil.getAccessVisibility(psiAnnotation);
    if (null != methodVisibility) {
      target.addAll(createRequiredArgsConstructor(psiClass, methodVisibility, psiAnnotation, getStaticConstructorName(psiAnnotation)));
    }
  }

  @NotNull
  public Collection<PsiMethod> createRequiredArgsConstructor(@NotNull PsiClass psiClass, @PsiModifier.ModifierConstant @NotNull String methodModifier, @NotNull PsiAnnotation psiAnnotation, @Nullable String staticName) {
    return createRequiredArgsConstructor(psiClass, methodModifier, psiAnnotation, staticName, false);
  }

  @NotNull
  public Collection<PsiMethod> createRequiredArgsConstructor(@NotNull PsiClass psiClass, @PsiModifier.ModifierConstant @NotNull String methodModifier, @NotNull PsiAnnotation psiAnnotation, @Nullable String staticName, boolean skipConstructorIfAnyConstructorExists) {
    final Collection<PsiField> allReqFields = getRequiredFields(psiClass);

    return createConstructorMethod(psiClass, methodModifier, psiAnnotation, false, allReqFields, staticName, skipConstructorIfAnyConstructorExists);
  }

  @Override
  public LombokPsiElementUsage checkFieldUsage(@NotNull PsiField psiField, @NotNull PsiAnnotation psiAnnotation) {
    final PsiClass containingClass = psiField.getContainingClass();
    if (null != containingClass) {
      if (PsiClassUtil.getNames(getRequiredFields(containingClass)).contains(psiField.getName())) {
        return LombokPsiElementUsage.WRITE;
      }
    }
    return LombokPsiElementUsage.NONE;
  }
}
