package CUHA.homepage.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// http://localhost:8080/api/calendar?year=2025&month=5

// 달력 불러오기는 기능만 구현
// 전체적인 코드 수정 예정

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarRESTController {

    @GetMapping
    public Map<String, Object> getCalendarData(@RequestParam int year, @RequestParam int month) {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int startDay = firstDay.getDayOfWeek().getValue() % 7;
        int lastDay = firstDay.lengthOfMonth();

        LocalDate[][] daysOfMonth = new LocalDate[6][7];
        int dayCounter = 1;

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (row == 0 && col < startDay) {
                    daysOfMonth[row][col] = null;
                } else if (dayCounter <= lastDay) {
                    daysOfMonth[row][col] = LocalDate.of(year, month, dayCounter++);
                } else {
                    daysOfMonth[row][col] = null;
                }
            }
        }

        Map<String, Object> calendarData = new HashMap<>();

        calendarData.put("year", year);                 // 년 : 2025
        calendarData.put("month", month);               // 월 : 5
        calendarData.put("daysOfMonth", daysOfMonth);   // 이번달 날짜 배열
        calendarData.put("today", today);               // 오늘 : 2025-05-13

        return calendarData;
    }
}
