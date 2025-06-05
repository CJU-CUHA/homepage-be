package CUHA.homepage.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CTFTeamCountryRequestDto {

    @JsonProperty("country_place")
    private int countryPlace;

    @JsonProperty("team_id")
    private int teamId;

    private double points;

    @JsonProperty("team_country")
    private String teamCountry;

    private int place;

    @JsonProperty("team_name")
    private String teamName;

    private int events;

}
