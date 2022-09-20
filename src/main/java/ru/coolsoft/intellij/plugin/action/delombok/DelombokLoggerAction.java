package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.log.CommonsLogProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.CustomLogProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.FloggerProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.JBossLogProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.Log4j2Processor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.Log4jProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.LogProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.Slf4jProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.log.XSlf4jProcessor;

public class DelombokLoggerAction extends AbstractDelombokAction {
  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(
      ApplicationManager.getApplication().getService(CommonsLogProcessor.class),
      ApplicationManager.getApplication().getService(JBossLogProcessor.class),
      ApplicationManager.getApplication().getService(Log4jProcessor.class),
      ApplicationManager.getApplication().getService(Log4j2Processor.class),
      ApplicationManager.getApplication().getService(LogProcessor.class),
      ApplicationManager.getApplication().getService(Slf4jProcessor.class),
      ApplicationManager.getApplication().getService(XSlf4jProcessor.class),
      ApplicationManager.getApplication().getService(FloggerProcessor.class),
      ApplicationManager.getApplication().getService(CustomLogProcessor.class));
  }
}
