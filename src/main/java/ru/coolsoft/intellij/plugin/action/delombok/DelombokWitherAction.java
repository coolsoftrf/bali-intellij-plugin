package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.WitherProcessor;
import ru.coolsoft.intellij.plugin.processor.field.WitherFieldProcessor;

public class DelombokWitherAction extends AbstractDelombokAction {
  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(
      ApplicationManager.getApplication().getService(WitherProcessor.class),
      ApplicationManager.getApplication().getService(WitherFieldProcessor.class));
  }
}
