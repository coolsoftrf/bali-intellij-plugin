package ru.coolsoft.intellij.plugin.resolver;

import static ru.coolsoft.intellij.plugin.LombokClassNames.MAIN_LOMBOK_CLASSES;

import com.intellij.codeInsight.daemon.quickFix.ExternalLibraryResolver;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ExternalLibraryDescriptor;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ThreeState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import ru.coolsoft.intellij.plugin.Version;

public class LombokExternalLibraryResolver extends ExternalLibraryResolver {

  private final Set<String> allLombokPackages;
  private final Map<String, String> simpleNameToFQNameMap;

  private static final ExternalLibraryDescriptor LOMBOK = new ExternalLibraryDescriptor("org.projectlombok", "lombok",
                                                                                        null, null, Version.LAST_LOMBOK_VERSION);

  public LombokExternalLibraryResolver() {
    allLombokPackages = MAIN_LOMBOK_CLASSES.stream().map(StringUtil::getPackageName).collect(Collectors.toUnmodifiableSet());
    simpleNameToFQNameMap = MAIN_LOMBOK_CLASSES.stream().collect(Collectors.toMap(StringUtil::getShortName, Function.identity()));
  }

  @Nullable
  @Override
  public ExternalClassResolveResult resolveClass(@NotNull String shortClassName,
                                                 @NotNull ThreeState isAnnotation,
                                                 @NotNull Module contextModule) {
    if (isAnnotation == ThreeState.YES && simpleNameToFQNameMap.containsKey(shortClassName)) {
      return new ExternalClassResolveResult(simpleNameToFQNameMap.get(shortClassName), LOMBOK);
    }
    return null;
  }

  @Nullable
  @Override
  public ExternalLibraryDescriptor resolvePackage(@NotNull String packageName) {
    if (allLombokPackages.contains(packageName)) {
      return LOMBOK;
    }
    return null;
  }
}
