package ru.coolsoft.intellij.plugin.provider;

import com.intellij.codeInsight.daemon.ImplicitUsageProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import ru.coolsoft.intellij.plugin.processor.LombokPsiElementUsage;

/**
 * Provides implicit usages of lombok fields
 */
public class LombokImplicitUsageProvider implements ImplicitUsageProvider {

  @Override
  public boolean isImplicitUsage(@NotNull PsiElement element) {
    return isImplicitWrite(element) || isImplicitRead(element);
  }

  @Override
  public boolean isImplicitRead(@NotNull PsiElement element) {
    return checkUsage(element, LombokPsiElementUsage.READ);
  }

  @Override
  public boolean isImplicitWrite(@NotNull PsiElement element) {
    return checkUsage(element, LombokPsiElementUsage.WRITE);
  }

  private boolean checkUsage(PsiElement element, LombokPsiElementUsage elementUsage) {
    boolean result = false;
    if (element instanceof PsiField) {
      final LombokProcessorProvider processorProvider = LombokProcessorProvider.getInstance(element.getProject());
      final Collection<LombokProcessorData> applicableProcessors = processorProvider.getApplicableProcessors((PsiField) element);

      for (LombokProcessorData processorData : applicableProcessors) {
        final LombokPsiElementUsage psiElementUsage = processorData.getProcessor().checkFieldUsage((PsiField) element, processorData.getPsiAnnotation());
        if (elementUsage == psiElementUsage || LombokPsiElementUsage.READ_WRITE == psiElementUsage) {
          result = true;
          break;
        }
      }

    }
    return result;
  }

}
