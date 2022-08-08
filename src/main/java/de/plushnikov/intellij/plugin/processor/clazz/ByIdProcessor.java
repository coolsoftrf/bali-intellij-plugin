package de.plushnikov.intellij.plugin.processor.clazz;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import de.plushnikov.intellij.plugin.LombokBundle;
import de.plushnikov.intellij.plugin.LombokClassNames;
import de.plushnikov.intellij.plugin.problem.ProblemBuilder;
import de.plushnikov.intellij.plugin.psi.LombokLightMethodBuilder;
import de.plushnikov.intellij.plugin.util.LombokProcessorUtil;
import de.plushnikov.intellij.plugin.util.PsiMethodUtil;

/**
 * Inspect and validate {@code @ById} annotation on a class
 * Creates {@code static byId} method for this class
 *
 * @author Plushnikov Michail
 */
public class ByIdProcessor extends AbstractClassProcessor {
  private final static String BY_ID_METHOD_NAME = "byId";

  public ByIdProcessor() {
    super(PsiMethod.class, LombokClassNames.BY_ID);
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
    return validateAnnotationOnRightType(psiClass, builder)
      && validateName(psiClass, builder)
      && validateVisibility(psiAnnotation)
      && validateReference(psiClass, builder);
  }

  private boolean validateAnnotationOnRightType(@NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
    if (!psiClass.isEnum()) {
      builder.addError(LombokBundle.message("inspection.message.byid.only.supported.on.enum.type"));
      return false;
    }
    return true;
  }

  private boolean validateName(PsiClass psiClass, ProblemBuilder builder) {
    if (psiClass.getQualifiedName() == null) {
      builder.addError(LombokBundle.message("inspection.message.byid.class.cannot.be.placed"));
      return false;
    }
    return true;
  }

  private boolean validateVisibility(@NotNull PsiAnnotation psiAnnotation) {
    final String methodVisibility = LombokProcessorUtil.getMethodModifier(psiAnnotation);
    return null != methodVisibility;
  }

  private boolean validateReference(PsiClass psiClass, ProblemBuilder builder) {
    if (getRefField(psiClass) == null) {
      builder.addError(LombokBundle.message("inspection.message.byid.requires.byidref"));
      return false;
    }
    return true;
  }

  @Override
  protected void generatePsiElements(@NotNull PsiClass psiClass, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final String methodVisibility = LombokProcessorUtil.getMethodModifier(psiAnnotation);
    if (methodVisibility != null) {
      target.add(createByIdMethod(psiClass, Objects.requireNonNull(getRefField(psiClass)).getType(), methodVisibility));
    }
  }

  @Nullable
  private PsiField getRefField(@NotNull PsiClass psiClass) {
    for (PsiField field : psiClass.getFields()) {
      if (field.hasAnnotation(LombokClassNames.BY_ID_REF_FIELD)) {
        return field;
      }
    }
    return null;
  }

  @NotNull
  public PsiMethod createByIdMethod(@NotNull PsiClass psiClass, @NotNull PsiType parameterType, @NotNull String methodModifier) {
    LombokLightMethodBuilder methodBuilder = new LombokLightMethodBuilder(psiClass.getManager(), BY_ID_METHOD_NAME)
      .withMethodReturnType(PsiType.getTypeByName(Objects.requireNonNull(psiClass.getQualifiedName()), psiClass.getProject(), psiClass.getResolveScope()))
      .withParameter("id", parameterType)
      .withContainingClass(psiClass)
      .withNavigationElement(psiClass);
    if (StringUtil.isNotEmpty(methodModifier)) {
      methodBuilder.withModifier(methodModifier);
    }
    methodBuilder.withModifier(PsiModifier.STATIC);

    final String blockText = "return null;";
    methodBuilder.withBody(PsiMethodUtil.createCodeBlockFromText(blockText, methodBuilder));

    return methodBuilder;
  }
}
