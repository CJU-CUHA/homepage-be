package CUHA.homepage.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CTFTeamListRequestDto {
    private List<String> aliases;
    private String country;
    private boolean academic;
    private int id;
    private String name;
}