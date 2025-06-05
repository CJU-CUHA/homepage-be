package CUHA.homepage.security.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CTFTeamDetailResponseDto {

    private final CTFTeamDetailRequestDto teamDetail;

    public CTFTeamDetailResponseDto(CTFTeamDetailRequestDto teamDetail) {
        this.teamDetail = teamDetail;
    }

}
