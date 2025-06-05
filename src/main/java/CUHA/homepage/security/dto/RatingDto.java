package CUHA.homepage.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {

    @JsonProperty("rating_place")
    private int ratingPlace;

    @JsonProperty("organizer_points")
    private int organizerPoints;

    @JsonProperty("rating_points")
    private double ratingPoints;

    @JsonProperty("country_place")
    private int countryPlace;

}
