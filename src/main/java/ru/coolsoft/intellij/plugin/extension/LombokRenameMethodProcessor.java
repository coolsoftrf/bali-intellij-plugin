package ru.coolsoft.intellij.plugin.extension;

import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.coolsoft.intellij.plugin.psi.LombokLightFieldBuilder;
import ru.coolsoft.intellij.plugin.psi.LombokLightMethodBuilder;

/**
 * RenameProcessor for replacement of lombok virtual methods/fields with root elements
 */
public class LombokRenameMethodProcessor extends RenamePsiElementProcessor {

  @Override
  public boolean canProcessElement(@NotNull PsiElement elem) {
    return (elem instanceof LombokLightMethodBuilder || elem instanceof LombokLightFieldBuilder)
      && !(elem.getNavigationElement() instanceof PsiAnnotation);
  }

  @Override
  @Nullable
  public PsiElement substituteElementToRename(@NotNull PsiElement elem, @Nullable Editor editor) {
    return elem.getNavigationElement();
  }
}
