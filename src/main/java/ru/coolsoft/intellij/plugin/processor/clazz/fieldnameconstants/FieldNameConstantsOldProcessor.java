package ru.coolsoft.intellij.plugin.processor.clazz.fieldnameconstants;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.*;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.problem.ProblemBuilder;
import ru.coolsoft.intellij.plugin.problem.ProblemEmptyBuilder;
import ru.coolsoft.intellij.plugin.processor.LombokPsiElementUsage;
import ru.coolsoft.intellij.plugin.processor.clazz.AbstractClassProcessor;
import ru.coolsoft.intellij.plugin.processor.field.FieldNameConstantsFieldProcessor;
import ru.coolsoft.intellij.plugin.thirdparty.LombokUtils;
import ru.coolsoft.intellij.plugin.util.LombokProcessorUtil;
import ru.coolsoft.intellij.plugin.util.PsiAnnotationSearchUtil;
import ru.coolsoft.intellij.plugin.util.PsiClassUtil;

/**
 * Inspect and validate @FieldNameConstants lombok annotation on a class
 * Creates string constants containing the field name for each field of this class
 * Used for lombok v1.16.22 to lombok v1.18.2 only!
 *
 * @author Plushnikov Michail
 */
public class FieldNameConstantsOldProcessor extends AbstractClassProcessor {

  public FieldNameConstantsOldProcessor() {
    super(PsiField.class, LombokClassNames.FIELD_NAME_CONSTANTS);
  }

  private FieldNameConstantsFieldProcessor getFieldNameConstantsFieldProcessor() {
    return ApplicationManager.getApplication().getService(FieldNameConstantsFieldProcessor.class);
  }

  @Override
  protected boolean supportAnnotationVariant(@NotNull PsiAnnotation psiAnnotation) {
    // old version of @FieldNameConstants has a attributes "prefix" and "suffix", the new one not
    return null != psiAnnotation.findAttributeValue("prefix");
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
    final boolean result = validateAnnotationOnRightType(psiClass, builder) && LombokProcessorUtil.isLevelVisible(psiAnnotation);
    if (result) {
      final Collection<PsiField> psiFields = filterFields(psiClass);
      FieldNameConstantsFieldProcessor fieldProcessor = getFieldNameConstantsFieldProcessor();
      for (PsiField psiField : psiFields) {
        fieldProcessor.checkIfFieldNameIsValidAndWarn(psiAnnotation, psiField, builder);
      }
    }
    return result;
  }

  private boolean validateAnnotationOnRightType(@NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
    boolean result = true;
    if (psiClass.isAnnotationType() || psiClass.isInterface()) {
      builder.addError(LombokBundle.message("inspection.message.field.name.constants.only.supported.on.class.enum.or.field.type"));
      result = false;
    }
    return result;
  }

  @Override
  protected void generatePsiElements(@NotNull PsiClass psiClass, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final Collection<PsiField> psiFields = filterFields(psiClass);
    FieldNameConstantsFieldProcessor fieldProcessor = getFieldNameConstantsFieldProcessor();
    for (PsiField psiField : psiFields) {
      if (fieldProcessor.checkIfFieldNameIsValidAndWarn(psiAnnotation, psiField, ProblemEmptyBuilder.getInstance())) {
        target.add(fieldProcessor.createFieldNameConstant(psiField, psiClass, psiAnnotation));
      }
    }
  }

  @NotNull
  private Collection<PsiField> filterFields(@NotNull PsiClass psiClass) {
    final Collection<PsiField> psiFields = new ArrayList<>();

    FieldNameConstantsFieldProcessor fieldProcessor = getFieldNameConstantsFieldProcessor();
    for (PsiField psiField : PsiClassUtil.collectClassFieldsIntern(psiClass)) {
      boolean useField = true;
      PsiModifierList modifierList = psiField.getModifierList();
      if (null != modifierList) {
        //Skip static fields.
        useField = !modifierList.hasModifierProperty(PsiModifier.STATIC);
        //Skip fields having same annotation already
        useField &= PsiAnnotationSearchUtil.isNotAnnotatedWith(psiField, fieldProcessor.getSupportedAnnotationClasses());
        //Skip fields that start with $
        useField &= !psiField.getName().startsWith(LombokUtils.LOMBOK_INTERN_FIELD_MARKER);
      }

      if (useField) {
        psiFields.add(psiField);
      }
    }
    return psiFields;
  }

  @Override
  public LombokPsiElementUsage checkFieldUsage(@NotNull PsiField psiField, @NotNull PsiAnnotation psiAnnotation) {
    final PsiClass containingClass = psiField.getContainingClass();
    if (null != containingClass) {
      if (PsiClassUtil.getNames(filterFields(containingClass)).contains(psiField.getName())) {
        return LombokPsiElementUsage.USAGE;
      }
    }
    return LombokPsiElementUsage.NONE;
  }

}
