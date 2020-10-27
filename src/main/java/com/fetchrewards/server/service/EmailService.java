package com.fetchrewards.server.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  public Integer uniqueCount(List<String> emails) {
    Map<String, Integer> map = new HashMap<>();

    for (String email : emails) {
      String matchingEmail = toMatchingEmail(email);
      map.merge(matchingEmail, 1, Integer::sum);
    }

    return map.size();
  }

  String toMatchingEmail(String target) {
    String delimiter = "@";

    List<String> parts = Collections
        .list(new StringTokenizer(target, delimiter))
        .stream()
        .map(Object::toString)
        .collect(Collectors.toList());

    if (parts.size() == 1) {
      return target;
    }

    String withOutDot = parts.get(0).replace(".", "");
    int indexOfPlus = withOutDot.indexOf('+');
    String matchingEmail = indexOfPlus != -1 ? withOutDot.substring(0, indexOfPlus) : withOutDot;
    return matchingEmail + delimiter + parts.get(1);
  }

}
