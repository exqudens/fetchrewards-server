package com.fetchrewards.server;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Component
public class HealthCheck implements HealthIndicator {

  @Override
  public Health health() {
    try {
      return Health.up().build();
    } catch (Throwable t) {
      log.error(t.getMessage(), t);
      return Health.down().withException(t).build();
    }
  }

}
