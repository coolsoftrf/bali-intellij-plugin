package ru.coolsoft.intellij.plugin.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

import ru.coolsoft.intellij.plugin.LombokBundle;
import ru.coolsoft.intellij.plugin.icon.LombokIcons;

public class LombokConfigFileType extends LanguageFileType {
  public static final LombokConfigFileType INSTANCE = new LombokConfigFileType();

  private LombokConfigFileType() {
    super(LombokConfigLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "LOMBOK_CONFIG";
  }

  @NotNull
  @Override
  @NlsContexts.Label
  public String getDescription() {
    return LombokBundle.message("label.lombok.config.file");
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "config";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return LombokIcons.CONFIG_FILE_ICON;
  }
}
