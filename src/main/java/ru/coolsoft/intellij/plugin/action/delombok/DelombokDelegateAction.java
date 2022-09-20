package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.field.DelegateFieldProcessor;
import ru.coolsoft.intellij.plugin.processor.method.DelegateMethodProcessor;

public class DelombokDelegateAction extends AbstractDelombokAction {

  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(
      ApplicationManager.getApplication().getService(DelegateFieldProcessor.class),
      ApplicationManager.getApplication().getService(DelegateMethodProcessor.class));
  }
}
