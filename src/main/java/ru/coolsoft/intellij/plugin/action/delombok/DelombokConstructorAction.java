package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.constructor.AllArgsConstructorProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.constructor.NoArgsConstructorProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.constructor.RequiredArgsConstructorProcessor;

public class DelombokConstructorAction extends AbstractDelombokAction {

  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(
      ApplicationManager.getApplication().getService(AllArgsConstructorProcessor.class),
      ApplicationManager.getApplication().getService(NoArgsConstructorProcessor.class),
      ApplicationManager.getApplication().getService(RequiredArgsConstructorProcessor.class));
  }

}
