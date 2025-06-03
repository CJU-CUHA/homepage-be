package CUHA.homepage.service;

import CUHA.homepage.security.dto.CTFTeamDetailResponseDto;
import CUHA.homepage.security.dto.CTFTeamResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CTFTimeService {
    CTFTeamResponseDto getCTFTimeTop(int limit) throws JsonProcessingException;
    CTFTeamResponseDto getCTFTimeTopByYear(int year, int limit) throws JsonProcessingException;
    CTFTeamDetailResponseDto getCTFTimeTopByCountry(String country) throws JsonProcessingException;
}