package CUHA.homepage.security.dto;

import CUHA.homepage.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ScheduleResponseDto {
    private List<Schedule> task;
    private Schedule schedule;

    public ScheduleResponseDto(List<Schedule> task) {
        this.task = task;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.schedule = schedule;
    }

}
