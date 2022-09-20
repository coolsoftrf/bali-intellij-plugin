package ru.coolsoft.intellij.plugin.intention;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.util.LombokLibraryUtil;

public abstract class AbstractLombokIntentionAction extends PsiElementBaseIntentionAction {

  @Override
  public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
    return LombokLibraryUtil.hasAnnotationsPackage(project);
  }
}
