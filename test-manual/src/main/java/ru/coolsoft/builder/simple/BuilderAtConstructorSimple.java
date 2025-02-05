package ru.coolsoft.builder.simple;

public class BuilderAtConstructorSimple {
  private int myInt;
  private String myString;

  @lombok.Builder
  public BuilderAtConstructorSimple(int myInt, String myString) {
    this.myInt = myInt + 1;
    this.myString = myString;
  }

  public static void main(String[] args) {
    BuilderAtConstructorSimple builderSimple = BuilderAtConstructorSimple.builder().myInt(123).myString("string").build();
    System.out.println(builderSimple);
  }
}
