package CUHA.homepage.service;

import CUHA.homepage.security.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CTFTimeService {
    CTFTeamResponseDto getCTFTimeTop(int limit) throws JsonProcessingException;
    CTFTeamResponseDto getCTFTimeTopByYear(int year, int limit) throws JsonProcessingException;
    CTFTeamCountryResponseDto getCTFTimeTopByCountry(String country) throws JsonProcessingException;

    CTFEventResponseDto getCTFTimeEvent(int limit, long start, long finish) throws JsonProcessingException;
    CTFEventResponseDto getCTFTimeEventById(int id) throws JsonProcessingException;

    CTFTeamListResponseDto getCTFTimeTeamList() throws JsonProcessingException;
    CTFTeamDetailResponseDto getCTFTimeTeamDetail(int id) throws JsonProcessingException;

    CTFEventResultResponseDto getCTFTimeEventResult() throws JsonProcessingException;
    CTFEventResultResponseDto getCTFTimeEventResultByYear(int year) throws JsonProcessingException;
}