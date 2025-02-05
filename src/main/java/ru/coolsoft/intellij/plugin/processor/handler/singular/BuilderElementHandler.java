package ru.coolsoft.intellij.plugin.processor.handler.singular;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiVariable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

import ru.coolsoft.intellij.plugin.processor.handler.BuilderInfo;
import ru.coolsoft.intellij.plugin.thirdparty.LombokUtils;

public interface BuilderElementHandler {

  String createSingularName(PsiAnnotation singularAnnotation, String psiFieldName);

  default String renderBuildPrepare(@NotNull BuilderInfo info) {
    return "";
  }

  default String renderSuperBuilderConstruction(@NotNull PsiVariable psiVariable, @NotNull String fieldName) {
    return "this." + psiVariable.getName() + "=b." + fieldName + ";\n";
  }

  default String renderToBuilderCall(@NotNull BuilderInfo info) {
    return calcBuilderMethodName(info) + '(' + info.getInstanceVariableName() + '.' + info.getVariable().getName() + ')';
  }

  Collection<PsiField> renderBuilderFields(@NotNull BuilderInfo info);

  default String calcBuilderMethodName(@NotNull BuilderInfo info) {
    return LombokUtils.buildAccessorName(info.getSetterPrefix(), info.getFieldName());
  }

  Collection<PsiMethod> renderBuilderMethod(@NotNull BuilderInfo info);

  List<String> getBuilderMethodNames(@NotNull String newName, @Nullable PsiAnnotation singularAnnotation);
}
