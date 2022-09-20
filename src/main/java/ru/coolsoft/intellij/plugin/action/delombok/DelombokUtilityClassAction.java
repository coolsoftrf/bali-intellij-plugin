package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.UtilityClassProcessor;

public class DelombokUtilityClassAction extends AbstractDelombokAction {
  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(true, ApplicationManager.getApplication().getService(UtilityClassProcessor.class));
  }
}
