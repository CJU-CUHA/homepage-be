package CUHA.homepage.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CTFEventScoreDto {

    @JsonProperty("team_id")
    private int teamId;

    private double points;
    private int place;
}
