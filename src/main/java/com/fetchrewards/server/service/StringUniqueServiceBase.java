package com.fetchrewards.server.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("StringUniqueServiceBase")
public class StringUniqueServiceBase implements StringUniqueService {

  @Override
  public Integer count(List<String> strings) {
    Map<String, Integer> map = strings
        .stream()
        .map(s -> Map.entry(filter(s), 1))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, Integer::sum));

    return map.size();
  }

  protected String filter(String string) {
    return string;
  }

}
