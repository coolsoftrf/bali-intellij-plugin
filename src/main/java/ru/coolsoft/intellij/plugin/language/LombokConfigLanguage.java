package ru.coolsoft.intellij.plugin.language;

import com.intellij.lang.Language;

public class LombokConfigLanguage extends Language {
  public static final LombokConfigLanguage INSTANCE = new LombokConfigLanguage();

  private LombokConfigLanguage() {
    super("Bali.Config");
  }
}
