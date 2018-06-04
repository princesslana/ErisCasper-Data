package com.github.princesslana.eriscasper.data.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.princesslana.eriscasper.data.Data;
import com.github.princesslana.eriscasper.data.DataException;
import com.github.princesslana.eriscasper.data.Snowflake;
import com.github.princesslana.eriscasper.data.resource.AuditLogChange;
import com.github.princesslana.eriscasper.data.resource.ImmutableAuditLogChange;
import com.github.princesslana.eriscasper.data.resource.Overwrite;
import com.github.princesslana.eriscasper.data.resource.Role;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Optional;

public class AuditLogChangeDeserializer extends StdDeserializer<AuditLogChange> {

  public AuditLogChangeDeserializer() {
    this(null);
  }

  private AuditLogChangeDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public AuditLogChange deserialize(JsonParser json, DeserializationContext context)
      throws IOException {
    JsonNode node = json.getCodec().readTree(json);
    try {
      return parseJson(node);
    } catch (DataException e) {
      throw context.instantiationException(AuditLogChange.class, e);
    }
  }

  private AuditLogChange parseJson(JsonNode node) throws DataException {
    String key = node.get("key").textValue();
    switch (key.toLowerCase()) {
      case "$add":
      case "$remove":
        return newArrayInstance(node, Role.class, key);
      case "permission_overwrites":
        return newArrayInstance(node, Overwrite.class, key);
      case "owner_id":
      case "afk_channel_id":
      case "widget_channel_id":
      case "system_channel_id":
      case "channel_id":
      case "inviter_id":
      case "id":
      case "application_id":
        return newInstance(node, Snowflake.class, key);
      case "widget_enabled":
      case "nsfw":
      case "temporary":
      case "deaf":
      case "mute":
      case "hoist":
      case "mentionable":
        return newInstance(node, Boolean.class, key);
      case "permissions":
      case "color":
      case "allow":
      case "deny":
      case "max_uses":
      case "uses":
      case "max_age":
      case "afk_timeout":
      case "mfa_level":
      case "verification_level":
      case "explicit_content_filter":
      case "default_message_notifications":
      case "position":
      case "bitrate":
      case "prune_delete_days":
        return newInstance(node, Integer.class, key);
      case "type":
      case "nick":
      case "avatar_hash":
      case "name":
      case "icon_hash":
      case "splash_hash":
      case "code":
      case "region":
      case "vanity_url_code":
      case "topic":
        return newInstance(node, String.class, key);
      default:
        // just for stuff not represented by discord (like system_channel_id wasn't)
        // we will catch them as they're reported, can't predict stuff with no documentation for it
        throw new DataException("Failed to load " + key + " from " + node.asText());
    }
  }

  private <T> AuditLogChange newInstance(JsonNode node, Class<T> clazz, String key) {
    T newValue = null;
    T oldValue = null;
    if (node.has("new_value")) {
      newValue = parse(node.get("new_value"), clazz);
    }
    if (node.has("old_value")) {
      oldValue = parse(node.get("old_value"), clazz);
    }
    return ImmutableAuditLogChange.builder()
        .key(key)
        .newValue(Optional.ofNullable(newValue))
        .oldValue(Optional.ofNullable(oldValue))
        .build();
  }

  private <T> AuditLogChange newArrayInstance(JsonNode node, Class<T> clazz, String key) {
    ImmutableList<T> newValue = ImmutableList.of();
    ImmutableList<T> oldValue = ImmutableList.of();
    if (node.has("new_value")) {
      newValue = parseArray(node.get("new_value"), clazz);
    }
    if (node.has("old_value")) {
      oldValue = parseArray(node.get("old_value"), clazz);
    }
    return ImmutableAuditLogChange.builder().key(key).newValue(newValue).oldValue(oldValue).build();
  }

  private <T> ImmutableList<T> parseArray(JsonNode node, Class<T> clazz) {
    ArrayNode array = (ArrayNode) node;
    ImmutableList.Builder<T> builder = ImmutableList.builder();
    array
        .elements()
        .forEachRemaining(
            part -> {
              T obj = parse(node, clazz);
              if (obj != null) {
                builder.add(obj);
              }
            });
    return builder.build();
  }

  private <T> T parse(JsonNode node, Class<T> clazz) {
    try {
      return Data.fromJson(node, clazz);
    } catch (DataException e) {
      return null;
    }
  }
}
