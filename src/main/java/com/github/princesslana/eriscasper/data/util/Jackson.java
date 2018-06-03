package com.github.princesslana.eriscasper.data.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.princesslana.eriscasper.data.resource.AuditLogChange;

public class Jackson {
  private Jackson() {}

  public static ObjectMapper newObjectMapper() {
    ObjectMapper jackson = new ObjectMapper();
    jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    jackson.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    jackson.registerModule(new GuavaModule());
    jackson.registerModule(new Jdk8Module());
    jackson.registerModule(new JavaTimeModule());
    SimpleModule module = new SimpleModule();
    module.addDeserializer(AuditLogChange.class, new AuditLogChangeDeserializer());
    jackson.registerModule(module);
    return jackson;
  }
}
