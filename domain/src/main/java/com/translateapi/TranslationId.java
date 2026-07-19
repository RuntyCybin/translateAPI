package com.translateapi;

import java.util.Objects;
import java.util.UUID;

public record TranslationId(UUID translationId) {
  public TranslationId {
    Objects.requireNonNull(translationId, "the value cannot be null");
  }

  public static TranslationId newID() {
    return new TranslationId(UUID.randomUUID());
  }
}
