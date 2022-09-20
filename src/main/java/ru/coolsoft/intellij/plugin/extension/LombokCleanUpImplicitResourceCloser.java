package ru.coolsoft.intellij.plugin.extension;

import com.intellij.codeInspection.resources.ImplicitResourceCloser;
import com.intellij.psi.PsiVariable;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationSearchUtil;

/**
 * Implement additional way to close AutoCloseables by @lombok.Cleanup for IntelliJ
 */
public class LombokCleanUpImplicitResourceCloser implements ImplicitResourceCloser {

  @Override
  public boolean isSafelyClosed(@NotNull PsiVariable variable) {
    return PsiAnnotationSearchUtil.isAnnotatedWith(variable, LombokClassNames.CLEANUP);
  }
}
