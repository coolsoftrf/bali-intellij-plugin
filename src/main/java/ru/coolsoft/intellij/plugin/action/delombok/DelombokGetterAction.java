package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.GetterProcessor;
import ru.coolsoft.intellij.plugin.processor.field.GetterFieldProcessor;

public class DelombokGetterAction extends AbstractDelombokAction {

  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(
      ApplicationManager.getApplication().getService(GetterProcessor.class),
      ApplicationManager.getApplication().getService(GetterFieldProcessor.class));
  }
}
