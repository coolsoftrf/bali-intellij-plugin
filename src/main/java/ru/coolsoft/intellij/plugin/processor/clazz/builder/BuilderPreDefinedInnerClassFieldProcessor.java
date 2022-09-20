package ru.coolsoft.intellij.plugin.processor.clazz.builder;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.processor.handler.BuilderHandler;
import ru.coolsoft.intellij.plugin.processor.handler.BuilderInfo;
import ru.coolsoft.intellij.plugin.util.PsiClassUtil;

/**
 * Creates fields for a @Builder inner class if it is predefined.
 *
 * @author Michail Plushnikov
 */
public class BuilderPreDefinedInnerClassFieldProcessor extends AbstractBuilderPreDefinedInnerClassProcessor {

  public BuilderPreDefinedInnerClassFieldProcessor() {
    super(PsiField.class, LombokClassNames.BUILDER);
  }

  @Override
  protected BuilderHandler getBuilderHandler() {
    return ApplicationManager.getApplication().getService(BuilderHandler.class);
  }

  @Override
  protected Collection<? extends PsiElement> generatePsiElements(@NotNull PsiClass psiParentClass, @Nullable PsiMethod psiParentMethod, @NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiBuilderClass) {
    final Collection<String> existedFieldNames = PsiClassUtil.collectClassFieldsIntern(psiBuilderClass).stream()
      .map(PsiField::getName)
      .collect(Collectors.toSet());

    final List<BuilderInfo> builderInfos = getBuilderHandler().createBuilderInfos(psiAnnotation, psiParentClass, psiParentMethod, psiBuilderClass);
    return builderInfos.stream()
      .filter(info -> info.notAlreadyExistingField(existedFieldNames))
      .map(BuilderInfo::renderBuilderFields)
      .flatMap(Collection::stream)
      .collect(Collectors.toList());
  }
}
