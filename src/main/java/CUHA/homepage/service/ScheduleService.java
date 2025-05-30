package CUHA.homepage.service;

import CUHA.homepage.domain.Schedule;
import CUHA.homepage.security.dto.ScheduleRequestDto;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getSchedule(int year, int month);
    Schedule addSchedule(ScheduleRequestDto scheduleRequestDto);
    Schedule updateSchedule(ScheduleRequestDto scheduleRequestDto);
    Schedule deleteSchedule(ScheduleRequestDto scheduleRequestDto);
}