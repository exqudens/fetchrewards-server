package com.fetchrewards.server.controller;

import com.fetchrewards.server.service.StringUniqueService;
import java.util.List;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RestController
public class EmailController {

  @Qualifier("GmailUniqueService")
  @NonNull
  StringUniqueService service;

  @PostMapping(
      path = "/api/email/unique-count",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public Integer uniqueCount(@RequestBody List<String> emails) {
    log.debug(
        "emails.size: {}, emails: {}",
        emails.size(),
        emails.size() <= 10 ? emails : emails.subList(0, 10)
    );
    return service.count(emails);
  }

}
