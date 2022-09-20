package ru.coolsoft.intellij.plugin.language.psi;

import com.intellij.psi.tree.IElementType;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.language.LombokConfigLanguage;

public class LombokConfigTokenType extends IElementType {
  public LombokConfigTokenType(@NotNull @NonNls String debugName) {
    super(debugName, LombokConfigLanguage.INSTANCE);
  }

  @Override
  public String toString() {
    return "LombokConfigTokenType." + super.toString();
  }
}
