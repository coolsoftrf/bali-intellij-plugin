package ru.coolsoft.intellij.plugin.processor;

import ru.coolsoft.intellij.plugin.AbstractLombokParsingTestCase;

/**
 * Unit tests for IntelliJPlugin for Lombok, based on lombok test classes
 */
public class ConstructorTest extends AbstractLombokParsingTestCase {

  public void testConstructors$Constructors() {
    doTest(true);
  }

  public void testConstructors$ConflictingStaticConstructorNames() {
    doTest(true);
  }

  public void testConstructors$NoArgsConstructorForced() {
    doTest(true);
  }

  public void testConstructors$ConstructorEnum() {
    doTest(true);
  }

  public void testConstructors$RequiredArgsConstructorWithGeneric136() {
    doTest(true);
  }

  public void testConstructors$RequiredArgsConstructorWithGeneric157() {
    doTest(true);
  }

  public void testConstructors$RequiredArgsConstructorStaticNameAccess() {
    doTest(true);
  }

  public void testConstructors$AllArgsConstructorStaticNameAccess() {
    doTest(true);
  }
}
