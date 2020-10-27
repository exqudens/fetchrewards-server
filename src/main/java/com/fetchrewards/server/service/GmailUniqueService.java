package com.fetchrewards.server.service;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("GmailUniqueService")
public class GmailUniqueService extends StringUniqueServiceBase {

  @Override
  protected String filter(String email) {
    String delimiter = "@";

    List<String> parts = Collections
        .list(new StringTokenizer(email, delimiter))
        .stream()
        .map(Object::toString)
        .collect(Collectors.toList());

    if (parts.size() == 1) {
      return email;
    }

    String withOutDot = parts.get(0).replace(".", "");
    int indexOfPlus = withOutDot.indexOf('+');
    String matchingEmail = indexOfPlus != -1 ? withOutDot.substring(0, indexOfPlus) : withOutDot;
    return matchingEmail + delimiter + parts.get(1);
  }

}
