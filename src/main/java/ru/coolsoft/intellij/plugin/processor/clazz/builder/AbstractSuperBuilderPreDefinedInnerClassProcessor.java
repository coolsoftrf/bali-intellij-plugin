package ru.coolsoft.intellij.plugin.processor.clazz.builder;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import ru.coolsoft.intellij.plugin.problem.LombokProblem;
import ru.coolsoft.intellij.plugin.problem.ProblemBuilder;
import ru.coolsoft.intellij.plugin.problem.ProblemEmptyBuilder;
import ru.coolsoft.intellij.plugin.processor.clazz.AbstractClassProcessor;
import ru.coolsoft.intellij.plugin.processor.handler.SuperBuilderHandler;

public abstract class AbstractSuperBuilderPreDefinedInnerClassProcessor extends AbstractClassProcessor {

  AbstractSuperBuilderPreDefinedInnerClassProcessor(@NotNull Class<? extends PsiElement> supportedClass,
                                                    @NotNull String supportedAnnotationClass) {
    super(supportedClass, supportedAnnotationClass);
  }

  protected abstract SuperBuilderHandler getBuilderHandler();

  @NotNull
  @Override
  public List<? super PsiElement> process(@NotNull PsiClass psiClass, @Nullable String nameHint) {
    final Optional<PsiClass> parentClass = getSupportedParentClass(psiClass);
    final Optional<PsiAnnotation> psiAnnotation = parentClass.map(this::getSupportedAnnotation);
    if (psiAnnotation.isPresent()) {
      final PsiClass psiParentClass = parentClass.get();
      final PsiAnnotation psiBuilderAnnotation = psiAnnotation.get();
      // use parent class as source!
      if (validate(psiBuilderAnnotation, psiParentClass, ProblemEmptyBuilder.getInstance())) {
        return processAnnotation(psiParentClass, psiBuilderAnnotation, psiClass, nameHint);
      }
    }
    return Collections.emptyList();
  }

  private List<? super PsiElement> processAnnotation(@NotNull PsiClass psiParentClass, @NotNull PsiAnnotation psiAnnotation,
                                                     @NotNull PsiClass psiClass, @Nullable String nameHint) {
    SuperBuilderHandler builderHandler = getBuilderHandler();
    // use parent class as source!
    final String builderBaseClassName = builderHandler.getBuilderClassName(psiParentClass);

    List<? super PsiElement> result = new ArrayList<>();
    // apply only to inner BuilderClass
    final String psiClassName = psiClass.getName();
    if (builderBaseClassName.equals(psiClassName) && possibleToGenerateElementNamed(nameHint, psiClass, psiAnnotation)) {
      result.addAll(generatePsiElementsOfBaseBuilderClass(psiParentClass, psiAnnotation, psiClass));
    } else {
      // use parent class as source!
      final String builderImplClassName = builderHandler.getBuilderImplClassName(psiParentClass);
      if (builderImplClassName.equals(psiClassName) && possibleToGenerateElementNamed(nameHint, psiClass, psiAnnotation)) {
        result.addAll(generatePsiElementsOfImplBuilderClass(psiParentClass, psiAnnotation, psiClass));
      }
    }
    return result;
  }

  protected abstract Collection<? extends PsiElement> generatePsiElementsOfBaseBuilderClass(@NotNull PsiClass psiParentClass, @NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiBuilderClass);

  protected abstract Collection<? extends PsiElement> generatePsiElementsOfImplBuilderClass(@NotNull PsiClass psiParentClass, @NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiBuilderClass);

  @NotNull
  @Override
  public Collection<LombokProblem> verifyAnnotation(@NotNull PsiAnnotation psiAnnotation) {
    //do nothing
    return Collections.emptySet();
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
    return getBuilderHandler().validate(psiClass, psiAnnotation, builder);
  }

  @Override
  protected void generatePsiElements(@NotNull PsiClass psiClass, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    //do nothing
  }
}
