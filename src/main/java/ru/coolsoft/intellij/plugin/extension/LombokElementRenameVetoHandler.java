package ru.coolsoft.intellij.plugin.extension;

import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.refactoring.rename.PsiElementRenameHandler;
import com.intellij.refactoring.rename.RenameHandler;
import com.intellij.refactoring.util.CommonRefactoringUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.psi.LombokLightClassBuilder;
import ru.coolsoft.intellij.plugin.psi.LombokLightFieldBuilder;
import ru.coolsoft.intellij.plugin.psi.LombokLightMethodBuilder;

/**
 * "Rename"-Handler Vetoer to disable renaming not supported lombok generated  methods
 */
public class LombokElementRenameVetoHandler implements RenameHandler {
  @Override
  public boolean isAvailableOnDataContext(DataContext dataContext) {
    final PsiElement element = PsiElementRenameHandler.getElement(dataContext);
    return element instanceof LombokLightClassBuilder ||
      ((element instanceof LombokLightMethodBuilder || element instanceof LombokLightFieldBuilder)
        && element.getNavigationElement() instanceof PsiAnnotation);
  }

  @Override
  public boolean isRenaming(DataContext dataContext) {
    return isAvailableOnDataContext(dataContext);
  }

  @Override
  public void invoke(@NotNull Project project, Editor editor, PsiFile file, @Nullable DataContext dataContext) {
    invokeInner(project, editor);
  }

  @Override
  public void invoke(@NotNull Project project, @NotNull PsiElement[] elements, @Nullable DataContext dataContext) {
    Editor editor = dataContext == null ? null : CommonDataKeys.EDITOR.getData(dataContext);
    invokeInner(project, editor);
  }

  private void invokeInner(Project project, Editor editor) {
    CommonRefactoringUtil.showErrorHint(project, editor,
      RefactoringBundle.getCannotRefactorMessage(LombokBundle.message("dialog.message.this.element.cannot.be.renamed")),
      RefactoringBundle.message("rename.title"), null);
  }
}
