package ru.coolsoft.intellij.plugin.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.thirdparty.LombokUtils;

public class LombokLibraryUtil {
  public static boolean hasAnnotationsPackage(@NotNull Project project) {
    ApplicationManager.getApplication().assertReadAccessAllowed();
    return CachedValuesManager.getManager(project).getCachedValue(project, () -> {
      PsiPackage bPackage = JavaPsiFacade.getInstance(project).findPackage(LombokUtils.BALI_PACKAGE);
      return new CachedValueProvider.Result<>(bPackage != null, ProjectRootManager.getInstance(project));
    });
  }
}
