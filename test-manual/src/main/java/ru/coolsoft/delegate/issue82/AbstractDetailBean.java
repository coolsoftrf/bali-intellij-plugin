package ru.coolsoft.delegate.issue82;

import java.util.List;

public abstract class AbstractDetailBean<T> {
  protected abstract List<T> getResult();
}

