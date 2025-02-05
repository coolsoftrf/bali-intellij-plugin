package ru.coolsoft.intellij.plugin.processor.clazz.builder;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ru.coolsoft.intellij.plugin.problem.LombokProblem;
import ru.coolsoft.intellij.plugin.problem.ProblemBuilder;
import ru.coolsoft.intellij.plugin.problem.ProblemEmptyBuilder;
import ru.coolsoft.intellij.plugin.processor.clazz.AbstractClassProcessor;
import ru.coolsoft.intellij.plugin.processor.handler.BuilderHandler;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationSearchUtil;
import ru.coolsoft.intellij.plugin.util.PsiClassUtil;

public abstract class AbstractBuilderPreDefinedInnerClassProcessor extends AbstractClassProcessor {

  AbstractBuilderPreDefinedInnerClassProcessor(@NotNull Class<? extends PsiElement> supportedClass,
                                               @NotNull String supportedAnnotationClass) {
    super(supportedClass, supportedAnnotationClass);
  }

  @NotNull
  @Override
  public List<? super PsiElement> process(@NotNull PsiClass psiClass, @Nullable String nameHint) {
    final Optional<PsiClass> parentClass = getSupportedParentClass(psiClass);
    final Optional<PsiAnnotation> builderAnnotation = parentClass.map(this::getSupportedAnnotation);
    if (builderAnnotation.isPresent()) {
      final PsiClass psiParentClass = parentClass.get();
      final PsiAnnotation psiBuilderAnnotation = builderAnnotation.get();
      // use parent class as source!
      if (validate(psiBuilderAnnotation, psiParentClass, ProblemEmptyBuilder.getInstance())) {
        return processAnnotation(psiParentClass, null, psiBuilderAnnotation, psiClass, nameHint);
      }
    } else if (parentClass.isPresent()) {
      final PsiClass psiParentClass = parentClass.get();
      final Collection<PsiMethod> psiMethods = PsiClassUtil.collectClassMethodsIntern(psiParentClass);
      for (PsiMethod psiMethod : psiMethods) {
        final PsiAnnotation psiBuilderAnnotation = PsiAnnotationSearchUtil.findAnnotation(psiMethod, getSupportedAnnotationClasses());
        if (null != psiBuilderAnnotation) {
          final String builderClassNameOfThisMethod = getBuilderHandler().getBuilderClassName(psiParentClass, psiBuilderAnnotation, psiMethod);
          // check we found right method for this existing builder class
          if (Objects.equals(builderClassNameOfThisMethod, psiClass.getName())) {
            return processAnnotation(psiParentClass, psiMethod, psiBuilderAnnotation, psiClass, nameHint);
          }
        }
      }
    }
    return Collections.emptyList();
  }

  private List<? super PsiElement> processAnnotation(@NotNull PsiClass psiParentClass, @Nullable PsiMethod psiParentMethod,
                                                     @NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass,
                                                     @Nullable String nameHint) {
    // use parent class as source!
    final String builderClassName = getBuilderHandler().getBuilderClassName(psiParentClass, psiAnnotation, psiParentMethod);

    List<? super PsiElement> result = new ArrayList<>();
    // apply only to inner BuilderClass
    if (builderClassName.equals(psiClass.getName())
      && possibleToGenerateElementNamed(nameHint, psiClass, psiAnnotation)) {
      result.addAll(generatePsiElements(psiParentClass, psiParentMethod, psiAnnotation, psiClass));
    }
    return result;
  }

  protected abstract BuilderHandler getBuilderHandler();

  protected abstract Collection<? extends PsiElement> generatePsiElements(@NotNull PsiClass psiParentClass, @Nullable PsiMethod psiParentMethod, @NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiBuilderClass);

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
