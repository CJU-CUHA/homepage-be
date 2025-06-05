package CUHA.homepage.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CTFTeamListResponseDto {
    private int limit;
    private List<CTFTeamListRequestDto> teamList;

    public CTFTeamListResponseDto(List<CTFTeamListRequestDto> teamList) {
        this.limit = 200;
        this.teamList = teamList;
    }

}
