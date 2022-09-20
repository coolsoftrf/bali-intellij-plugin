package ru.coolsoft.intellij.plugin.action.inline;

import static com.intellij.refactoring.inline.InlineMethodHandler.performInline;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.refactoring.inline.JavaInlineActionHandler;

import ru.coolsoft.intellij.plugin.psi.LombokLightMethodBuilder;

/**
 * Custom InlineMethodHandler to support lombok generated methods
 */
public class LombokInlineMethodHandler extends JavaInlineActionHandler {

  private LombokInlineMethodHandler() {
  }

  @Override
  public boolean canInlineElement(PsiElement element) {
    return element instanceof LombokLightMethodBuilder && element.getLanguage() == JavaLanguage.INSTANCE;
  }

  @Override
  public void inlineElement(final Project project, Editor editor, PsiElement element) {
    performInline(project, editor, (PsiMethod) element, true);
  }
}
