package ru.coolsoft.delegate;

public interface GenericInterface {
  void test(int a);

  <T> T unwrap(Class<T> param);

}
