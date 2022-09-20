package ru.coolsoft.intellij.plugin.processor.handler.singular;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ru.coolsoft.intellij.plugin.processor.handler.BuilderInfo;

class EmptyBuilderElementHandler implements BuilderElementHandler {

  @Override
  public Collection<PsiField> renderBuilderFields(@NotNull BuilderInfo info) {
    return Collections.emptyList();
  }

  @Override
  public Collection<PsiMethod> renderBuilderMethod(@NotNull BuilderInfo info) {
    return Collections.emptyList();
  }

  @Override
  public String calcBuilderMethodName(@NotNull BuilderInfo info) {
    return "";
  }

  @Override
  public List<String> getBuilderMethodNames(@NotNull String newName, @Nullable PsiAnnotation singularAnnotation) {
    return Collections.emptyList();
  }

  @Override
  public String createSingularName(PsiAnnotation singularAnnotation, String psiFieldName) {
    return psiFieldName;
  }

}
