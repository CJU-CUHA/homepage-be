package CUHA.homepage.security.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CTFTeamResponseDto {

    private final String year;
    private final List<CTFTeamRequestDto> teamList;

    public CTFTeamResponseDto(String year, List<CTFTeamRequestDto> teamList) {
        this.year = year;
        this.teamList = teamList;
    }

}