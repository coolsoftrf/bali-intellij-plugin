package ru.coolsoft.intellij.plugin.processor;

import com.intellij.openapi.application.ApplicationManager;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

import ru.coolsoft.intellij.plugin.processor.clazz.ByIdProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.UtilityClassProcessor;
import ru.coolsoft.intellij.plugin.processor.clazz.ValueProcessor;
import ru.coolsoft.intellij.plugin.processor.modifier.FieldDefaultsModifierProcessor;
import ru.coolsoft.intellij.plugin.processor.modifier.ModifierProcessor;
import ru.coolsoft.intellij.plugin.processor.modifier.UtilityClassModifierProcessor;
import ru.coolsoft.intellij.plugin.processor.modifier.ValModifierProcessor;
import ru.coolsoft.intellij.plugin.processor.modifier.ValueModifierProcessor;

public final class LombokProcessorManager {
  @NotNull
  public static Collection<Processor> getLombokProcessors() {
    return Arrays.asList(

      ApplicationManager.getApplication().getService(ByIdProcessor.class),

      ApplicationManager.getApplication().getService(ValueProcessor.class),

      ApplicationManager.getApplication().getService(UtilityClassProcessor.class)
    );
  }

  @NotNull
  public static Collection<ModifierProcessor> getLombokModifierProcessors() {
    return Arrays.asList(
      ApplicationManager.getApplication().getService(FieldDefaultsModifierProcessor.class),
      ApplicationManager.getApplication().getService(UtilityClassModifierProcessor.class),
      ApplicationManager.getApplication().getService(ValModifierProcessor.class),
      ApplicationManager.getApplication().getService(ValueModifierProcessor.class));
  }
}
