package com.github.princesslana.eriscasper.data.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.princesslana.eriscasper.data.Snowflake;
import com.github.princesslana.eriscasper.data.resource.AuditLogChange;
import com.github.princesslana.eriscasper.data.resource.ImmutableAuditLogChange;
import com.github.princesslana.eriscasper.data.resource.Overwrite;
import com.github.princesslana.eriscasper.data.resource.Role;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public class AuditLogChangeDeserializer extends StdDeserializer<AuditLogChange> {

  public AuditLogChangeDeserializer() {
    super((Class<?>) null);
  }

  @Override
  public AuditLogChange deserialize(JsonParser json, DeserializationContext context)
      throws IOException {
    JsonNode node = json.getCodec().readTree(json);
    return parseJson(node, context, json.getCodec());
  }

  private AuditLogChange parseJson(JsonNode node, DeserializationContext context, ObjectCodec codec)
      throws JsonProcessingException {
    String key = node.get("key").textValue();
    switch (key.toLowerCase()) {
      case "$add":
      case "$remove":
        return newArrayInstance(node, Role.class, key, codec);
      case "permission_overwrites":
        return newArrayInstance(node, Overwrite.class, key, codec);
      case "owner_id":
      case "afk_channel_id":
      case "widget_channel_id":
      case "system_channel_id":
      case "channel_id":
      case "inviter_id":
      case "id":
      case "application_id":
        return newInstance(node, Snowflake.class, key, codec);
      case "widget_enabled":
      case "nsfw":
      case "temporary":
      case "deaf":
      case "mute":
      case "hoist":
      case "mentionable":
        return newInstance(node, Boolean.class, key, codec);
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
        return newInstance(node, Integer.class, key, codec);
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
        return newInstance(node, String.class, key, codec);
      default:
        // just for stuff not represented by discord (like system_channel_id wasn't)
        // we will catch them as they're reported, can't predict stuff with no documentation for it
        throw context.instantiationException(
            AuditLogChange.class, "Failed to load " + key + " from " + node.asText());
    }
  }

  private <T> AuditLogChange newInstance(
      JsonNode node, Class<T> clazz, String key, ObjectCodec codec) throws JsonProcessingException {
    T newValue = null;
    T oldValue = null;
    if (node.has("new_value")) {
      newValue = parse(node.get("new_value"), clazz, codec);
    }
    if (node.has("old_value")) {
      oldValue = parse(node.get("old_value"), clazz, codec);
    }
    return ImmutableAuditLogChange.builder()
        .key(key)
        .newValue(Optional.ofNullable(newValue))
        .oldValue(Optional.ofNullable(oldValue))
        .build();
  }

  private <T> AuditLogChange newArrayInstance(
      JsonNode node, Class<T> clazz, String key, ObjectCodec codec) throws JsonProcessingException {
    ImmutableList<T> newValue = ImmutableList.of();
    ImmutableList<T> oldValue = ImmutableList.of();
    if (node.has("new_value")) {
      newValue = parseArray(node.get("new_value"), clazz, codec);
    }
    if (node.has("old_value")) {
      oldValue = parseArray(node.get("old_value"), clazz, codec);
    }
    return ImmutableAuditLogChange.builder().key(key).newValue(newValue).oldValue(oldValue).build();
  }

  private <T> ImmutableList<T> parseArray(JsonNode node, Class<T> clazz, ObjectCodec codec)
      throws JsonProcessingException {
    ArrayNode array = (ArrayNode) node;
    ImmutableList.Builder<T> builder = ImmutableList.builder();
    Iterator<JsonNode> arrayTree = array.elements();
    while (arrayTree.hasNext()) {
      builder.add(parse(arrayTree.next(), clazz, codec));
    }
    return builder.build();
  }

  private <T> T parse(JsonNode node, Class<T> clazz, ObjectCodec codec)
      throws JsonProcessingException {
    return codec.treeToValue(node, clazz);
  }
}
