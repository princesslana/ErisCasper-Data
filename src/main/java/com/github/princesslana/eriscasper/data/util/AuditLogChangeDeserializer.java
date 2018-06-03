package com.github.princesslana.eriscasper.data.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.princesslana.eriscasper.data.resource.AuditLogChange;
import java.io.IOException;

public class AuditLogChangeDeserializer extends StdDeserializer<AuditLogChange> {

  AuditLogChangeDeserializer() {
    this(null);
  }

  private AuditLogChangeDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public AuditLogChange deserialize(JsonParser json, DeserializationContext context)
      throws IOException {
    JsonNode node = json.getCodec().readTree(json);
    return AuditLogChange.parseJson(node);
  }
}
