package CUHA.homepage.service.impl;

import CUHA.homepage.security.dto.CTFTeamDetailRequestDto;
import CUHA.homepage.security.dto.CTFTeamDetailResponseDto;
import CUHA.homepage.security.dto.CTFTeamResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import CUHA.homepage.security.dto.CTFTeamRequestDto;
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
        System.out.println(json);
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
    public CTFTeamDetailResponseDto getCTFTimeTopByCountry(String country) throws JsonProcessingException {
        String url = String.format("https://ctftime.org/api/v1/top-by-country/%s/", country);
        String json = restTemplate.getForObject(url, String.class);

        List<CTFTeamDetailRequestDto> teamList = objectMapper.readValue(json, new TypeReference<>() {});

        return new CTFTeamDetailResponseDto(teamList);
    }
}