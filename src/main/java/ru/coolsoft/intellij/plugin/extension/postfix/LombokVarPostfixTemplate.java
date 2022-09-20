package ru.coolsoft.intellij.plugin.extension.postfix;

public class LombokVarPostfixTemplate extends LombokVarValPostfixTemplate {

  public LombokVarPostfixTemplate() {
    super("varl", "lombok.var name = expr", "lombok.var");
  }
}
