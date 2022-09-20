package ru.coolsoft.intellij.plugin.resolver;

import com.intellij.codeInsight.daemon.quickFix.ExternalLibraryResolver;
import com.intellij.util.ThreeState;
import ru.coolsoft.intellij.plugin.AbstractLombokLightCodeInsightTestCase;
import ru.coolsoft.intellij.plugin.LombokClassNames;

public class LombokExternalLibraryResolverTest extends AbstractLombokLightCodeInsightTestCase {

  public void testResolverConstruction() {
    LombokExternalLibraryResolver cut = new LombokExternalLibraryResolver();
    ExternalLibraryResolver.ExternalClassResolveResult result = cut.resolveClass("Data",
                                                                                        ThreeState.YES,
                                                                                        getModule());
    assertNotNull(result);
    assertEquals(LombokClassNames.DATA, result.getQualifiedClassName());
  }
}
