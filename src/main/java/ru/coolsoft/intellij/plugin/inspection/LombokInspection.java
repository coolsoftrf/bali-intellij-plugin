package ru.coolsoft.intellij.plugin.inspection;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.*;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.problem.LombokProblem;
import ru.coolsoft.intellij.plugin.processor.Processor;
import ru.coolsoft.intellij.plugin.processor.ValProcessor;
import ru.coolsoft.intellij.plugin.provider.LombokProcessorProvider;
import ru.coolsoft.intellij.plugin.psi.LombokLightMethodBuilder;

/**
 * @author Plushnikov Michail
 */
public class LombokInspection extends LombokJavaInspectionBase {

  private final ValProcessor valProcessor;

  public LombokInspection() {
    valProcessor = ApplicationManager.getApplication().getService(ValProcessor.class);
  }

  @NotNull
  @Override
  protected PsiElementVisitor createVisitor(@NotNull final ProblemsHolder holder, final boolean isOnTheFly) {
    return new LombokElementVisitor(holder);
  }

  private class LombokElementVisitor extends JavaElementVisitor {

    private final ProblemsHolder holder;

    LombokElementVisitor(ProblemsHolder holder) {
      this.holder = holder;
    }

    @Override
    public void visitLocalVariable(PsiLocalVariable variable) {
      super.visitLocalVariable(variable);

      valProcessor.verifyVariable(variable, holder);
    }

    @Override
    public void visitParameter(PsiParameter parameter) {
      super.visitParameter(parameter);

      valProcessor.verifyParameter(parameter, holder);
    }

    @Override
    public void visitAnnotation(PsiAnnotation annotation) {
      super.visitAnnotation(annotation);

      final Collection<LombokProblem> problems = new HashSet<>();

      final LombokProcessorProvider processorProvider = LombokProcessorProvider.getInstance(annotation.getProject());
      for (Processor inspector : processorProvider.getProcessors(annotation)) {
        problems.addAll(inspector.verifyAnnotation(annotation));
      }

      for (LombokProblem problem : problems) {
        holder.registerProblem(annotation, problem.getMessage(), problem.getHighlightType(), problem.getQuickFixes());
      }
    }

    /**
     * Check MethodCallExpressions for calls for default (argument less) constructor
     * Produce an error if resolved constructor method is build by lombok and contains some arguments
     */
    @Override
    public void visitMethodCallExpression(PsiMethodCallExpression methodCall) {
      super.visitMethodCallExpression(methodCall);

      PsiExpressionList list = methodCall.getArgumentList();
      PsiReferenceExpression referenceToMethod = methodCall.getMethodExpression();

      boolean isThisOrSuper = referenceToMethod.getReferenceNameElement() instanceof PsiKeyword;
      final int parameterCount = list.getExpressions().length;
      if (isThisOrSuper && parameterCount == 0) {

        JavaResolveResult[] results = referenceToMethod.multiResolve(true);
        JavaResolveResult resolveResult = results.length == 1 ? results[0] : JavaResolveResult.EMPTY;
        PsiElement resolved = resolveResult.getElement();

        if (resolved instanceof LombokLightMethodBuilder && ((LombokLightMethodBuilder) resolved).getParameterList().getParameters().length != 0) {
          holder.registerProblem(methodCall, LombokBundle.message("inspection.message.default.constructor.doesn.t.exist"), ProblemHighlightType.ERROR);
        }
      }
    }
  }
}
