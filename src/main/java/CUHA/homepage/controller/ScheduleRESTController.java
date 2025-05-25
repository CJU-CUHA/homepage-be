package CUHA.homepage.calendar.controller;

import CUHA.homepage.calendar.domain.ScheduleRequestDto;
import CUHA.homepage.calendar.domain.ScheduleResponseDto;
import CUHA.homepage.calendar.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleRESTController {

    private final ScheduleService scheduleService;

    @GetMapping()
    public ResponseEntity<?> getScheduleData(@RequestParam int year, @RequestParam int month) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.getSchedule(year, month));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("일정 데이터를 불러오는 중 오류가 발생했습니다.");
        }
    }

    // 나머지 기능 구현 후 구현 예정
    @GetMapping("/all")
    public ScheduleResponseDto getAllScheduleData() {
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDto dto) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.addSchedule(dto));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("일정 데이터를 추가하는 중 오류가 발생했습니다.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleRequestDto dto) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.updateSchedule(dto));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("일정 데이터를 수정하는 중 오류가 발생했습니다.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSchedule(@RequestBody ScheduleRequestDto dto) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.deleteSchedule(dto));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("일정 데이터를 삭제하는 중 오류가 발생했습니다.");
        }
    }
}
