package ru.coolsoft.intellij.plugin.extension;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.SourceTreeToPsiMap;
import com.intellij.psi.impl.source.tree.ChangeUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.impl.source.tree.TreeGenerator;
import com.intellij.util.CharTable;

import org.jetbrains.annotations.Nullable;

import ru.coolsoft.intellij.plugin.psi.LombokLightMethodBuilder;

/**
 * @author Plushnikov Michail
 */
public class LombokLightMethodTreeGenerator implements TreeGenerator {

  public LombokLightMethodTreeGenerator() {
  }

  @Override
  @Nullable
  public TreeElement generateTreeFor(PsiElement original, CharTable table, PsiManager manager) {
    TreeElement result = null;
    if (original instanceof LombokLightMethodBuilder) {
      result = ChangeUtil.copyElement((TreeElement) SourceTreeToPsiMap.psiElementToTree(original), table);
    }
    return result;
  }
}
