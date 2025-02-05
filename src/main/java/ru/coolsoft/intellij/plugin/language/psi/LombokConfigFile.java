package ru.coolsoft.intellij.plugin.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;

import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

import ru.coolsoft.intellij.plugin.language.LombokConfigFileType;
import ru.coolsoft.intellij.plugin.language.LombokConfigLanguage;

public class LombokConfigFile extends PsiFileBase {
  public LombokConfigFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, LombokConfigLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return LombokConfigFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "LombokConfigFile";
  }

  @Override
  public Icon getIcon(int flags) {
    return super.getIcon(flags);
  }
}
