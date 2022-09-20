package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import ru.coolsoft.intellij.plugin.processor.clazz.EqualsAndHashCodeProcessor;

public class DelombokEqualsAndHashCodeAction extends AbstractDelombokAction {

  @Override
  protected DelombokHandler createHandler() {
    return new DelombokHandler(ApplicationManager.getApplication().getService(EqualsAndHashCodeProcessor.class));
  }
}
