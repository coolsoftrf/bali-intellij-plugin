package ru.coolsoft.val;

import lombok.val;

public class ValWithLabel {
  {
    LABEL:
    for (val x : new String[0]) {
      if (x.toLowerCase() == null) {
        continue LABEL;
      }
    }
  }
}
