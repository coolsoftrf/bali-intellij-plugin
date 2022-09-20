package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.builder.SuperBuilderClassProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.builder.SuperBuilderPreDefinedInnerClassFieldProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.builder.SuperBuilderPreDefinedInnerClassMethodProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.builder.SuperBuilderProcessor;

public class DelombokSuperBuilderAction extends AbstractDelombokAction {

  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(true,
                               ApplicationManager.getApplication().getService(SuperBuilderPreDefinedInnerClassFieldProcessor.class),
                               ApplicationManager.getApplication().getService(SuperBuilderPreDefinedInnerClassMethodProcessor.class),
                               ApplicationManager.getApplication().getService(SuperBuilderClassProcessor.class),
                               ApplicationManager.getApplication().getService(SuperBuilderProcessor.class));
  }
}
