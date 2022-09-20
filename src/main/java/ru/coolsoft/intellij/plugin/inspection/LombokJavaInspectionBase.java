package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.util.LombokLibraryUtil;

public abstract class LombokJavaInspectionBase extends AbstractBaseJavaLocalInspectionTool {
  @Override
  public final @NotNull
  PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    if (!LombokLibraryUtil.hasAnnotationsPackage(holder.getProject())) {
      return PsiElementVisitor.EMPTY_VISITOR;
    }

    return createVisitor(holder, isOnTheFly);
  }

  @NotNull
  protected abstract PsiElementVisitor createVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly);
}
