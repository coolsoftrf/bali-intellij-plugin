package ru.coolsoft.intellij.plugin.processor.method;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.problem.ProblemBuilder;
import ru.coolsoft.intellij.plugin.processor.handler.BuilderHandler;

/**
 * Inspect and validate @Builder lombok annotation on a method
 * Creates inner class for a builder pattern
 *
 * @author Tomasz Kalkosiński
 * @author Michail Plushnikov
 */
public class BuilderClassMethodProcessor extends AbstractMethodProcessor {

  public BuilderClassMethodProcessor() {
    super(PsiClass.class, LombokClassNames.BUILDER);
  }

  @Override
  protected boolean possibleToGenerateElementNamed(@Nullable String nameHint, @NotNull PsiClass psiClass,
                                                   @NotNull PsiAnnotation psiAnnotation, @NotNull PsiMethod psiMethod) {
    if (null == nameHint) {
      return true;
    }

    final String innerBuilderClassName = getHandler().getBuilderClassName(psiClass, psiAnnotation, psiMethod);
    return Objects.equals(nameHint, innerBuilderClassName);
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiMethod psiMethod, @NotNull ProblemBuilder builder) {
    return getHandler().validate(psiMethod, psiAnnotation, builder);
  }

  @Override
  protected void processIntern(@NotNull PsiMethod psiMethod, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final PsiClass psiClass = psiMethod.getContainingClass();
    if (null != psiClass) {
      final BuilderHandler builderHandler = getHandler();
      builderHandler.createBuilderClassIfNotExist(psiClass, psiMethod, psiAnnotation).ifPresent(target::add);
    }
  }

  private BuilderHandler getHandler() {
    return ApplicationManager.getApplication().getService(BuilderHandler.class);
  }
}
