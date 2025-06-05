package CUHA.homepage.security.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CTFEventResponseDto {
    private int count;
    private List<CTFEventRequestDto> event;
    private CTFEventRequestDto oneEvent;

    public CTFEventResponseDto(List<CTFEventRequestDto> event) {
        this.event = event;
        this.count = event.size();
    }

    public CTFEventResponseDto(CTFEventRequestDto oneEvent) {
        this.oneEvent = oneEvent;
    }
}