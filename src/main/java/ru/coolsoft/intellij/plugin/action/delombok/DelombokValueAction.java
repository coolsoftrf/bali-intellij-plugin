package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.ValueProcessor;

public class DelombokValueAction extends AbstractDelombokAction {
  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(ApplicationManager.getApplication().getService(ValueProcessor.class));
  }
}
