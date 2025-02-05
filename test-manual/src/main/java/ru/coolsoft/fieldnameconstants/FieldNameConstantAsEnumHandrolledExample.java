package ru.coolsoft.fieldnameconstants;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(asEnum = true, innerTypeName = "NAMES")
public class FieldNameConstantAsEnumHandrolledExample {
  private String stringField;
  private int intField;

  @FieldNameConstants.Exclude
  private float excluded;

  public int getInt() {
    return 1;
  }

  public enum NAMES {
    existingField
  }

  public static void main(String[] args) {
    System.out.println(FieldNameConstantAsEnumHandrolledExample.NAMES.intField);

    FieldNameConstantAsEnumHandrolledExample.NAMES iAmAField = NAMES.stringField;
    System.out.println(iAmAField);

    print(iAmAField);
    print(NAMES.valueOf("stringField"));
    print(NAMES.valueOf("intField"));
    assert iAmAField.compareTo(NAMES.valueOf("stringField")) == 0;

    for (NAMES value : NAMES.values()) {
      System.out.println("enum name is " + value.name());
      print(value);
    }

    assert NAMES.stringField.ordinal() == 0;
    assert NAMES.intField.ordinal() == 1;
    assert NAMES.existingField.ordinal() == 2;
  }

  private static void print(NAMES iAmAField) {
    switch (iAmAField) {
      case stringField:
        System.out.println("stringField");
        break;
      case intField:
        System.out.println("intField");
        break;
    }
  }

}
