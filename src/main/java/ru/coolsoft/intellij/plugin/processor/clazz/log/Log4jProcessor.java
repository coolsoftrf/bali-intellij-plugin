package ru.coolsoft.intellij.plugin.processor.clazz.log;

import ru.coolsoft.intellij.plugin.LombokClassNames;

/**
 * @author Plushnikov Michail
 */
public class Log4jProcessor extends AbstractTopicSupportingSimpleLogProcessor {

  private static final String LOGGER_TYPE = "org.apache.log4j.Logger";
  private static final String LOGGER_INITIALIZER = "org.apache.log4j.Logger.getLogger(%s)";

  public Log4jProcessor() {
    super(LombokClassNames.LOG_4_J, LOGGER_TYPE, LOGGER_INITIALIZER, LoggerInitializerParameter.TYPE);
  }
}
