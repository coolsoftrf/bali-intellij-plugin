package ru.coolsoft.builder.importbuilder;

import ru.coolsoft.builder.BuilderExample;
import ru.coolsoft.builder.BuilderExample.BuilderExampleBuilder;
import ru.coolsoft.builder.importbuilder.otherpackage.Builder2Import;
import static ru.coolsoft.builder.importbuilder.otherpackage.Builder2Import.Builder2ImportBuilder;
import ru.coolsoft.builder.simple.BuilderSimple;
import ru.coolsoft.builder.simple.BuilderSimple.BuilderSimpleBuilder;

public class TestImportingBuilderClass {
  public static void main(String[] args) {
    BuilderSimpleBuilder builderSimple = BuilderSimple.builder();

    BuilderSimple simple = builderSimple.myInt(1).build();
    System.out.println(simple);

    BuilderExampleBuilder builderExampleBuilder = BuilderExample.builder();

    Builder2ImportBuilder builder2ImportBuilder = Builder2Import.builder();
  }
}
