package ru.coolsoft.intellij.plugin.processor.clazz.fieldnameconstants;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import ru.coolsoft.intellij.plugin.LombokClassNames;
import ru.coolsoft.intellij.plugin.processor.handler.FieldNameConstantsHandler;
import ru.coolsoft.intellij.plugin.psi.LombokLightClassBuilder;
import ru.coolsoft.intellij.plugin.util.PsiClassUtil;

/**
 * Inspect and validate @FieldNameConstants lombok annotation on a field
 * Creates Inner class containing string constants of the field name for each field of this class
 *
 * @author alanachtenberg
 */
public class FieldNameConstantsProcessor extends AbstractFieldNameConstantsProcessor {

  public FieldNameConstantsProcessor() {
    super(PsiClass.class, LombokClassNames.FIELD_NAME_CONSTANTS);
  }

  @Override
  protected void generatePsiElements(@NotNull PsiClass psiClass, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final Collection<PsiField> psiFields = filterFields(psiClass, psiAnnotation);
    if (!psiFields.isEmpty()) {
      final String typeName = FieldNameConstantsHandler.getTypeName(psiClass, psiAnnotation);
      Optional<PsiClass> existingClass = PsiClassUtil.getInnerClassInternByName(psiClass, typeName);
      if (existingClass.isEmpty()) {
        LombokLightClassBuilder innerClassOrEnum = FieldNameConstantsHandler.createInnerClassOrEnum(typeName, psiClass, psiAnnotation);
        if (innerClassOrEnum != null) {
          innerClassOrEnum.withFieldSupplier(() -> FieldNameConstantsHandler.createFields(innerClassOrEnum, psiFields));

          target.add(innerClassOrEnum);
        }
      }
    }
  }
}
