package com.github.princesslana.eriscasper.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.princesslana.eriscasper.data.util.Jackson;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

  public static <T> T fromJson(JsonNode json, Class<T> clazz) throws DataException {
    try {
      return jackson.treeToValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new DataException(e);
    }
  }

  public static String toJsonString(Object obj) throws DataException {
    try {
      return jackson.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new DataException(e);
    }
  }

  public static String toQueryString(Object obj) {
    return StreamSupport.stream(
            ((Iterable<Map.Entry<String, JsonNode>>) () -> jackson.valueToTree(obj).fields())
                .spliterator(),
            false)
        .map(e -> e.getKey() + "=" + e.getValue())
        .collect(Collectors.joining("&"));
  }
}
