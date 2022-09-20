package ru.coolsoft.intellij.plugin.action.delombok;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.processor.clazz.fieldnameconstants.FieldNameConstantsOldProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.fieldnameconstants.FieldNameConstantsPredefinedInnerClassFieldProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.fieldnameconstants.FieldNameConstantsProcessor;
import ru.coolsoft.intellij.plugin.processor.field.FieldNameConstantsFieldProcessor;

public class DelombokFieldNameConstantsAction extends AbstractDelombokAction {
  @Override
  @NotNull
  protected DelombokHandler createHandler() {
    return new DelombokHandler(true,
                               ApplicationManager.getApplication().getService(FieldNameConstantsOldProcessor.class),
                               ApplicationManager.getApplication().getService(FieldNameConstantsFieldProcessor.class),
                               ApplicationManager.getApplication().getService(FieldNameConstantsProcessor.class),
                               ApplicationManager.getApplication().getService(FieldNameConstantsPredefinedInnerClassFieldProcessor.class));
  }
}
