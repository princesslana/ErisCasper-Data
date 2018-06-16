package com.github.princesslana.eriscasper.data.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Optional;

public class Jackson {
  private Jackson() {}

  public static ObjectMapper newObjectMapper() {
    ObjectMapper jackson = new ObjectMapper();
    jackson.setDefaultPropertyInclusion(
        JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.ALWAYS)
            .withValueFilter(NotEmptyFilter.class)
            .withContentFilter(NotEmptyFilter.class));
    jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    jackson.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    jackson.registerModule(new GuavaModule());
    jackson.registerModule(new Jdk8Module());
    jackson.registerModule(new JavaTimeModule());
    return jackson;
  }

  private static class NotEmptyFilter {
    @Override
    public boolean equals(Object rhs) {
      return Optional.empty().equals(rhs);
    }
  }
}
