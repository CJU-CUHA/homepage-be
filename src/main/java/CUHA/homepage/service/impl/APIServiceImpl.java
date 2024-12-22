package CUHA.homepage.service.impl;

import CUHA.homepage.service.APIService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class APIServiceImpl implements APIService {
    @Override
    public List<String> saraminGetApi() {
        return List.of();
    }

    @Override
    public List<Map<String,String>> ctfTimeGetApi() throws IOException {
        //timestamp
        LocalDateTime now = LocalDateTime.now();

        // 2주일 후 시간
        LocalDateTime twoWeeksLater = now.plus(2, ChronoUnit.WEEKS);

        // LocalDateTime을 Instant로 변환하여 타임스탬프 (초 단위) 얻기
        Instant nowInstant = now.toInstant(ZoneOffset.UTC);
        Instant twoWeeksLaterInstant = twoWeeksLater.toInstant(ZoneOffset.UTC);
        //objectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //okhttp
        OkHttpClient okHttpClient = new OkHttpClient();
        String url="https://ctftime.org/api/v1/events/?limit=100&start=%d&finish=%d".formatted(nowInstant.getEpochSecond(),twoWeeksLaterInstant.getEpochSecond());
        Request request = new Request.Builder()
                .url(url)
                .header( "Accept", "application/json")
                .header("cookie","ss=eyJ1c2VybmFtZSI6ICJraW5nX2luZ19lIiwgImlkIjogMjE0Njk4LCAidHMiOiAxNzM0Nzg5MTcxfQ==:d0374277c9d1bdb1c789986821eca08e3cf2039b0ede26ae583f7440501ccc80")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            ResponseBody body = response.body();
            String jsonResponse = body.string();
            List<Map<String, Object>> events = objectMapper.readValue(jsonResponse, List.class);
            if(body != null){
//                System.out.println("Response:"+body.string());
//                JsonNode jsonNode=objectMapper.readTree(body.string());
                List<Map<String,String>> jsonEvents = new ArrayList<>();
                for (Map<String, Object> event : events) {
                    Map<String,String> jsonMap = new HashMap<>();
                    jsonMap.put("url",event.get("url").toString());
                    jsonMap.put("weight",String.valueOf(event.get("weight")));
                    jsonMap.put("start",event.get("start").toString());
                    jsonMap.put("finish",event.get("finish").toString());
                    List<Map<String,Object>> organizers = (List<Map<String,Object>>)event.get("organizers");

                    for (Map<String,Object> organizer : organizers) {
                        jsonMap.put("name",organizer.get("name").toString());

                    }
                    jsonEvents.add(jsonMap);
                }
                return jsonEvents;

            }
        }
        return null;
    }
}
