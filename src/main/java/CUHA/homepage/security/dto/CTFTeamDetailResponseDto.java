package CUHA.homepage.security.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CTFTeamDetailResponseDto {

    private final List<CTFTeamDetailRequestDto> teamList;

    public CTFTeamDetailResponseDto(List<CTFTeamDetailRequestDto> teamList) {
        this.teamList = teamList;
    }

}
