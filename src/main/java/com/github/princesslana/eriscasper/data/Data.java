package com.github.princesslana.eriscasper.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.princesslana.eriscasper.data.util.Jackson;
import java.io.IOException;

public class Data {

  private static final ObjectMapper jackson = Jackson.newObjectMapper();

  private Data() {}

  public static <T> T fromJson(String json, Class<T> clazz) throws DataException {
    try {
      return jackson.readValue(json, clazz);
    } catch (IOException e) {
      throw new DataException(e);
    }
  }
}
