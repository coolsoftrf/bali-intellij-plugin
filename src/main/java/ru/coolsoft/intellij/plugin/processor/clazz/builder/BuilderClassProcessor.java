package ru.coolsoft.intellij.plugin.processor.clazz.builder;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.problem.ProblemBuilder;
import ru.coolsoft.intellij.plugin.processor.clazz.AbstractClassProcessor;
import ru.coolsoft.intellij.plugin.processor.handler.BuilderHandler;

/**
 * Inspect and validate @Builder lombok annotation on a class
 * Creates inner class for a builder pattern
 *
 * @author Tomasz Kalkosiński
 * @author Michail Plushnikov
 */
public class BuilderClassProcessor extends AbstractClassProcessor {

  public BuilderClassProcessor() {
    super(PsiClass.class, LombokClassNames.BUILDER);
  }

  private BuilderHandler getBuilderHandler() {
    return ApplicationManager.getApplication().getService(BuilderHandler.class);
  }

  @Override
  protected boolean possibleToGenerateElementNamed(@Nullable String nameHint, @NotNull PsiClass psiClass,
                                                   @NotNull PsiAnnotation psiAnnotation) {
    if (null == nameHint) {
      return true;
    }

    final String innerBuilderClassName = getBuilderHandler().getBuilderClassName(psiClass, psiAnnotation, null);
    return Objects.equals(nameHint, innerBuilderClassName);
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
    return getBuilderHandler().validate(psiClass, psiAnnotation, builder);
  }

  @Override
  protected void generatePsiElements(@NotNull PsiClass psiClass, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    getBuilderHandler().createBuilderClassIfNotExist(psiClass, null, psiAnnotation).ifPresent(target::add);
  }
}
