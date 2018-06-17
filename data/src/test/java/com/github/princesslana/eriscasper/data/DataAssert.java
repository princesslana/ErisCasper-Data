package com.github.princesslana.eriscasper.data;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

public class DataAssert {
  private DataAssert() {}

  public static <T> AbstractObjectAssert<?, T> thatFromJson(String payload, Class<T> cls) {
    try {
      T data = Data.fromJson(payload, cls);

      return Assertions.assertThat(data);
    } catch (DataException e) {
      throw new AssertionError("Could not parse payload", e);
    }
  }

  public static <T> AbstractObjectAssert<?, T> thatJsonFromFile(String path, Class<T> cls) {
    try {
      String resource = "/com/github/princesslana/eriscasper/data/" + path;
      URL url = DataAssert.class.getResource(resource);

      if (url == null) {
        throw new AssertionError("Could not find resource: " + resource);
      }

      String payload = Resources.asByteSource(url).asCharSource(Charsets.UTF_8).read();

      return thatFromJson(payload, cls);
    } catch (IOException e) {
      throw new AssertionError("Could not load payload", e);
    }
  }
}
