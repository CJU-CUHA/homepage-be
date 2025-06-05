package CUHA.homepage.security.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class CTFEventResultResponseDto {
    private final Map<String, CTFEventDetailDto> eventResults;

    public CTFEventResultResponseDto(Map<String, CTFEventDetailDto> eventResults) {
        this.eventResults = eventResults;
    }
}