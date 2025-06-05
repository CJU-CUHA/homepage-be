package CUHA.homepage.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CTFTeamDetailRequestDto {
    private boolean academic;

    @JsonProperty("primary_alias")
    private String primaryAlias;

    private String name;
    private Map<String, RatingDto> rating;
    private String logo;
    private String country;
    private int id;
    private List<String> aliases;
}
