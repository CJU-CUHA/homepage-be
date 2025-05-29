package CUHA.homepage.controller;

import CUHA.homepage.domain.Title;
import CUHA.homepage.security.dto.ScheduleRequestDto;
import CUHA.homepage.security.dto.ScheduleResponseDto;
import CUHA.homepage.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleRESTController {

    private final ScheduleService scheduleService;

    @GetMapping("/list")
    public ResponseEntity<?> getScheduleList(@RequestParam int year, @RequestParam int month) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.getSchedule(year, month));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getSchedule(@PathVariable LocalDate date) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.getSchedule(date));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(e.getMessage());
        }
    }

    // 예외처리 없어도 될 듯
    @GetMapping("/all")
    public ResponseEntity<?> getAllScheduleList() {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.getAllSchedule());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDto dto) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.addSchedule(dto));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleRequestDto dto) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.updateSchedule(dto));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSchedule(@RequestBody ScheduleRequestDto dto) {
        try {
            ScheduleResponseDto response = new ScheduleResponseDto(scheduleService.deleteSchedule(dto));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(e.getMessage());
        }
    }

    @GetMapping("/title")
    public Title[] getTitleData() {
        return Title.values();
    }
}
