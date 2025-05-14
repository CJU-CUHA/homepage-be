package CUHA.homepage.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface APIService {
    List<String> saraminGetApi();
    List<Map<String,String>> ctfTimeGetApi() throws IOException;

}
