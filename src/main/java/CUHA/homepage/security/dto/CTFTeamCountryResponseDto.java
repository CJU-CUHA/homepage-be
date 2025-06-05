package CUHA.homepage.security.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CTFTeamCountryResponseDto {

    private final List<CTFTeamCountryRequestDto> teamList;

    public CTFTeamCountryResponseDto(List<CTFTeamCountryRequestDto> teamList) {
        this.teamList = teamList;
    }

}
