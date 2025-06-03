package CUHA.homepage.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CTFTeamRequestDto {

    @JsonProperty("team_name")
    private String teamName;

    private double points;

    @JsonProperty("team_id")
    private int teamId;

}