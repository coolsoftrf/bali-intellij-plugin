package ru.coolsoft.intellij.plugin.processor.clazz.log;

import ru.coolsoft.intellij.plugin.LombokClassNames;

/**
 * @author Plushnikov Michail
 */
public class LogProcessor extends AbstractTopicSupportingSimpleLogProcessor {

  private static final String LOGGER_TYPE = "java.util.logging.Logger";
  private static final String LOGGER_INITIALIZER = "java.util.logging.Logger.getLogger(%s)";

  public LogProcessor() {
    super(LombokClassNames.JAVA_LOG, LOGGER_TYPE, LOGGER_INITIALIZER, LoggerInitializerParameter.NAME);
  }
}
