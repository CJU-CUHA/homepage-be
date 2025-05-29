package CUHA.homepage.service.impl;

import CUHA.homepage.domain.Schedule;
import CUHA.homepage.security.dto.ScheduleRequestDto;
import CUHA.homepage.repository.ScheduleRepository;
import CUHA.homepage.service.ScheduleService;
import CUHA.homepage.domain.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getSchedule(int year, int month) {
        if (year <= 0 || month < 1 || month > 12) {
            throw new IllegalArgumentException("올바른 년도 및 월을 입력하세요.");
        }

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return scheduleRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Schedule getSchedule(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("날짜를 입력해야 합니다.");
        }

        return scheduleRepository.findByDate(date);
    }

    @Override
    public List<Schedule> getAllSchedule() {

        return scheduleRepository.findAll();
    }

    @Override
    public Schedule addSchedule(ScheduleRequestDto scheduleRequestDto) {
        LocalDate date = scheduleRequestDto.getDate();
        Title title = scheduleRequestDto.getTitle();
        String subtitle = scheduleRequestDto.getSubtitle();
        Long userId = scheduleRequestDto.getUserId();

        if (scheduleRequestDto.getDate() == null ||
                scheduleRequestDto.getTitle() == null) {
            throw new IllegalArgumentException("날짜, 타이틀은 필수 항목입니다.");
        }

        Schedule schedule = Schedule.builder()
                .date(date)
                .title(title)
                .subtitle(subtitle)
                .userId(userId)
                .build();

        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(ScheduleRequestDto scheduleRequestDto) {
        Long id = scheduleRequestDto.getId();

        // 임시 예외생성, 코드 분리 예정
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        if (scheduleRequestDto.getDate() != null) {
            schedule.setDate(scheduleRequestDto.getDate());
        }
        if (scheduleRequestDto.getTitle() != null) {
            schedule.setTitle(scheduleRequestDto.getTitle());
        }
        if (scheduleRequestDto.getSubtitle() != null) {
            schedule.setSubtitle(scheduleRequestDto.getSubtitle());
        }
        if (scheduleRequestDto.getUserId() != null) {
            schedule.setUserId(scheduleRequestDto.getUserId());
        }

        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule deleteSchedule(ScheduleRequestDto scheduleRequestDto) {
        Long id = scheduleRequestDto.getId();

        // 임시 예외생성, 코드 분리 예정
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        scheduleRepository.delete(schedule);

        return schedule;
    }
}
