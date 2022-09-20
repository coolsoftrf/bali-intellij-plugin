package ru.coolsoft.intellij.plugin.inspection.modifiers;

import static com.intellij.psi.PsiModifier.FINAL;

import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiVariable;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.processor.ValProcessor;

public class RedundantModifiersOnValLombokAnnotationInspection extends LombokRedundantModifierInspection {

  public RedundantModifiersOnValLombokAnnotationInspection() {
    super(null, new RedundantModifiersInfo(RedundantModifiersInfoType.VARIABLE, null, LombokBundle.message("inspection.message.val.already.marks.variables.final"), FINAL) {
      @Override
      public boolean shouldCheck(PsiModifierListOwner psiModifierListOwner) {
        PsiVariable psiVariable = (PsiVariable) psiModifierListOwner;
        return ValProcessor.isVal(psiVariable);
      }
    });
  }
}
