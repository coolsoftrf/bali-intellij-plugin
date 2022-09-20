package ru.coolsoft.intellij.plugin.inspection.modifiers;

import static com.intellij.psi.PsiModifier.FINAL;
import static com.intellij.psi.PsiModifier.STATIC;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.LombokClassNames;

public class RedundantModifiersOnUtilityClassLombokAnnotationInspection extends LombokRedundantModifierInspection {

  public RedundantModifiersOnUtilityClassLombokAnnotationInspection() {
    super(
      LombokClassNames.UTILITY_CLASS,
      new RedundantModifiersInfo(RedundantModifiersInfoType.CLASS, null, LombokBundle.message("inspection.message.utility.class.already.marks.class.final"), FINAL),
      new RedundantModifiersInfo(RedundantModifiersInfoType.FIELD, null, LombokBundle.message("inspection.message.utility.class.already.marks.fields.static"), STATIC),
      new RedundantModifiersInfo(RedundantModifiersInfoType.METHOD, null, LombokBundle.message("inspection.message.utility.class.already.marks.methods.static"), STATIC),
      new RedundantModifiersInfo(RedundantModifiersInfoType.INNER_CLASS, null,
                                 LombokBundle.message("inspection.message.utility.class.already.marks.inner.classes.static"), STATIC)
    );
  }
}
