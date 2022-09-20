package ru.coolsoft.intellij.plugin.processor;

import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.problem.LombokProblem;
import ru.coolsoft.intellij.plugin.problem.ProblemNewBuilder;
import ru.coolsoft.intellij.plugin.quickfix.PsiQuickFixFactory;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationUtil;

/**
 * Inspect and validate @Synchronized lombok annotation
 *
 * @author Plushnikov Michail
 */
public class SynchronizedProcessor extends AbstractProcessor {

  public SynchronizedProcessor() {
    super(PsiElement.class, LombokClassNames.SYNCHRONIZED);
  }

  @NotNull
  @Override
  public Collection<PsiAnnotation> collectProcessedAnnotations(@NotNull PsiClass psiClass) {
    return Collections.emptyList();
  }

  @NotNull
  @Override
  public Collection<LombokProblem> verifyAnnotation(@NotNull PsiAnnotation psiAnnotation) {
    final ProblemNewBuilder problemNewBuilder = new ProblemNewBuilder(2);

    PsiMethod psiMethod = PsiTreeUtil.getParentOfType(psiAnnotation, PsiMethod.class);
    if (null != psiMethod) {
      if (psiMethod.hasModifierProperty(PsiModifier.ABSTRACT)) {
        problemNewBuilder.addError(LombokBundle.message("inspection.message.synchronized.legal.only.on.concrete.methods"),
                                   PsiQuickFixFactory.createModifierListFix(psiMethod, PsiModifier.ABSTRACT, false, false)
        );
      }

      @NlsSafe final String lockFieldName = PsiAnnotationUtil.getStringAnnotationValue(psiAnnotation, "value", "");
      if (StringUtil.isNotEmpty(lockFieldName)) {
        final PsiClass containingClass = psiMethod.getContainingClass();

        if (null != containingClass) {
          final PsiField lockField = containingClass.findFieldByName(lockFieldName, true);
          if (null != lockField) {
            if (!lockField.hasModifierProperty(PsiModifier.FINAL)) {
              problemNewBuilder.addWarning(String.format(LombokBundle.message("inspection.message.synchronization.on.non.final.field.s"), lockFieldName),
                PsiQuickFixFactory.createModifierListFix(lockField, PsiModifier.FINAL, true, false));
            }
          } else {
            final PsiClassType javaLangObjectType = PsiType.getJavaLangObject(containingClass.getManager(), containingClass.getResolveScope());

            problemNewBuilder.addError(String.format(LombokBundle.message("inspection.message.field.s.does.not.exist"), lockFieldName),
              PsiQuickFixFactory.createNewFieldFix(containingClass, lockFieldName, javaLangObjectType, "new Object()", PsiModifier.PRIVATE, PsiModifier.FINAL));
          }
        }
      }
    } else {
      problemNewBuilder.addError(LombokBundle.message("inspection.message.synchronized.legal.only.on.methods"));
    }

    return problemNewBuilder.getProblems();
  }
}
