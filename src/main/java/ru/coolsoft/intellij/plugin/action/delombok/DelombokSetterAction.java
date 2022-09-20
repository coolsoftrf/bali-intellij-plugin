package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.SetterProcessor;
import ru.coolsoft.intellij.plugin.processor.field.SetterFieldProcessor;

public class DelombokSetterAction extends AbstractDelombokAction {
  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(
      ApplicationManager.getApplication().getService(SetterProcessor.class),
      ApplicationManager.getApplication().getService(SetterFieldProcessor.class));
  }
}
