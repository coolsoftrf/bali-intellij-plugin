package ru.coolsoft.getter;


import lombok.Getter;

public class GetterLazyInvalid {

  @Getter(lazy = true)
  private final String fieldName = "Something";
}
