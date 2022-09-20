package ru.coolsoft.intellij.plugin.lombokconfig;

import com.intellij.psi.util.PsiTreeUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.coolsoft.intellij.plugin.language.psi.LombokConfigCleaner;
import ru.coolsoft.intellij.plugin.language.psi.LombokConfigFile;
import ru.coolsoft.intellij.plugin.language.psi.LombokConfigProperty;

public final class LombokConfigUtil {

  private static final LombokConfigProperty[] EMPTY_LOMBOK_CONFIG_PROPERTIES = new LombokConfigProperty[0];
  private static final LombokConfigCleaner[] EMPTY_LOMBOK_CONFIG_CLEANERS = new LombokConfigCleaner[0];

  @NotNull
  public static LombokConfigProperty[] getLombokConfigProperties(@Nullable LombokConfigFile lombokConfigFile) {
    LombokConfigProperty[] result = PsiTreeUtil.getChildrenOfType(lombokConfigFile, LombokConfigProperty.class);
    return null == result ? EMPTY_LOMBOK_CONFIG_PROPERTIES : result;
  }

  @NotNull
  public static LombokConfigCleaner[] getLombokConfigCleaners(@Nullable LombokConfigFile lombokConfigFile) {
    LombokConfigCleaner[] result = PsiTreeUtil.getChildrenOfType(lombokConfigFile, LombokConfigCleaner.class);
    return null == result ? EMPTY_LOMBOK_CONFIG_CLEANERS : result;
  }
}
