package ru.coolsoft.intellij.plugin.inspection.modifiers;

import static com.intellij.psi.PsiModifier.FINAL;
import static com.intellij.psi.PsiModifier.PRIVATE;
import static com.intellij.psi.PsiModifier.STATIC;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.LombokClassNames;

/**
 * @author Rowicki Michał
 */
public class RedundantModifiersOnValueLombokAnnotationInspection extends LombokRedundantModifierInspection {

  public RedundantModifiersOnValueLombokAnnotationInspection() {
    super(
      LombokClassNames.VALUE,
      new RedundantModifiersInfo(RedundantModifiersInfoType.CLASS, null,
                                 LombokBundle.message("inspection.message.value.already.marks.class.final"), FINAL),
      new RedundantModifiersInfo(RedundantModifiersInfoType.FIELD, STATIC,
                                 LombokBundle.message("inspection.message.value.already.marks.non.static.fields.final"), FINAL),
      new RedundantModifiersInfo(RedundantModifiersInfoType.FIELD, STATIC,
                                 LombokBundle.message("inspection.message.value.already.marks.non.static.package.local.fields.private"), PRIVATE));
  }
}
