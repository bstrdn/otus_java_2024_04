package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import ru.otus.model.Measurement;

public class ResourcesFileLoader implements Loader {

  private final URL resource;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public ResourcesFileLoader(String fileName) {
    this.resource = getClass().getResource("/" + fileName);
  }

    @Override
    public List<Measurement> load() {
      try {
        return objectMapper.readValue(resource, new TypeReference<>() {});
      } catch (IOException e) {
        System.out.println(e.getMessage());
        return Collections.emptyList();
      }
    }
}