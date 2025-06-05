package CUHA.homepage.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CTFEventDetailDto {
    private String title;
    private List<CTFEventScoreDto> scores;
    private long time;
}
