package ru.coolsoft.findusages;

import lombok.Value;
import lombok.With;
import lombok.experimental.Wither;

@With
@Value
public class FindUsageWither {
  private int foo;
  private String bar;

  public static void main(String[] args) {
    FindUsageWither findUsageWither = new FindUsageWither(1, "bar");
    findUsageWither
      .withBar("myBar")
      .withFoo(1981);
    System.out.println("Bar is: " + findUsageWither.getBar());
    System.out.println("Foo is: " + findUsageWither.getFoo());
  }
}
