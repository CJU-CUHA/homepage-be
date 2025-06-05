package CUHA.homepage.service.impl;

import CUHA.homepage.security.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import CUHA.homepage.service.CTFTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Year;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CTFTimeServiceImpl implements CTFTimeService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public CTFTeamResponseDto getCTFTimeTop(int limit) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/top/?limit=%d", limit);
        String json = restTemplate.getForObject(url, String.class);

        String currentYear = String.valueOf(Year.now().getValue());
        Map<String, List<CTFTeamRequestDto>> dataMap = objectMapper.readValue(json, new TypeReference<>() {});
        List<CTFTeamRequestDto> teamList = dataMap.get(currentYear);

        return new CTFTeamResponseDto(currentYear, teamList);
    }

    @Override
    public CTFTeamResponseDto getCTFTimeTopByYear(int year, int limit) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/top/%d/?limit=%d", year, limit);
        String json = restTemplate.getForObject(url, String.class);

        String yearKey = String.valueOf(year);
        Map<String, List<CTFTeamRequestDto>> dataMap = objectMapper.readValue(json, new TypeReference<>() {});
        List<CTFTeamRequestDto> teamList = dataMap.get(yearKey);

        return new CTFTeamResponseDto(yearKey, teamList);
    }

    @Override
    public CTFTeamCountryResponseDto getCTFTimeTopByCountry(String country) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/top-by-country/%s/", country);
        String json = restTemplate.getForObject(url, String.class);

        List<CTFTeamCountryRequestDto> teamList = objectMapper.readValue(json, new TypeReference<>() {});

        return new CTFTeamCountryResponseDto(teamList);
    }



    @Override
    public CTFEventResponseDto getCTFTimeEvent(int limit, long start, long finish) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/events/?limit=%d&start=%d&finish=%d", limit, start, finish);
        String json = restTemplate.getForObject(url, String.class);

        List<CTFEventRequestDto> event = objectMapper.readValue(json, new TypeReference<>() {});

        return new CTFEventResponseDto(event);
    }

    @Override
    public CTFEventResponseDto getCTFTimeEventById(int id) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/events/%d/", id);
        String json = restTemplate.getForObject(url, String.class);

        CTFEventRequestDto event = objectMapper.readValue(json, CTFEventRequestDto.class);

        return new CTFEventResponseDto(event);
    }


    @Override
    public CTFTeamListResponseDto getCTFTimeTeamList() throws JsonProcessingException {
        String url = "https://ctftime.org/api/v1/teams/";
        String json = restTemplate.getForObject(url, String.class);

        // JSON 전체 파싱, result 추출
        JsonNode root = objectMapper.readTree(json);
        JsonNode resultNode = root.get("result");

        List<CTFTeamListRequestDto> teamList = objectMapper.readValue(resultNode.toString(), new TypeReference<>() {});

        return new CTFTeamListResponseDto(teamList);
    }

    @Override
    public CTFTeamDetailResponseDto getCTFTimeTeamDetail(int id) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/teams/%d/", id);
        String json = restTemplate.getForObject(url, String.class);

        CTFTeamDetailRequestDto teamDetail = objectMapper.readValue(json, CTFTeamDetailRequestDto.class);

        return new CTFTeamDetailResponseDto(teamDetail);
    }

    @Override
    public CTFEventResultResponseDto getCTFTimeEventResult() throws JsonProcessingException {
        String url = "https://ctftime.org/api/v1/results/";
        String json = restTemplate.getForObject(url, String.class);

        Map<String, CTFEventDetailDto> eventResults = objectMapper.readValue(json, new TypeReference<>() {});

        return new CTFEventResultResponseDto(eventResults);
    }

    @Override
    public CTFEventResultResponseDto getCTFTimeEventResultByYear(int year) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/results/%d/", year);
        String json = restTemplate.getForObject(url, String.class);

        Map<String, CTFEventDetailDto> eventResults = objectMapper.readValue(json, new TypeReference<>() {});

        return new CTFEventResultResponseDto(eventResults);
    }

}