package CUHA.homepage.controller;

import CUHA.homepage.security.dto.*;
import CUHA.homepage.service.CTFTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ctftime")
@RequiredArgsConstructor
public class CTFTimeRESTController {

    private final CTFTimeService ctfTimeService;

    @GetMapping("/top") // 현재 년도의 순위권 팀 출력
    public ResponseEntity<?> getCTFTimeTop(@RequestParam(defaultValue = "10") int limit) {
        try {
            CTFTeamResponseDto response = ctfTimeService.getCTFTimeTop(limit);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }

    @GetMapping("/top/year/{year}") // 입력한 년도의 순위권 팀 출력
    public ResponseEntity<?> getCTFTimeTopByYear(@PathVariable int year, @RequestParam(defaultValue = "10") int limit) {
        try {
            CTFTeamResponseDto response = ctfTimeService.getCTFTimeTopByYear(year, limit);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }

    @GetMapping("/top/country/{country}") // 입력한 지역의 순위권 팀 출력
    public ResponseEntity<?> getCTFTimeTopByCountry(@PathVariable String country) {
        try {
            CTFTeamCountryResponseDto response = ctfTimeService.getCTFTimeTopByCountry(country);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }



    @GetMapping("/events")
    public ResponseEntity<?> getCTFTimeEvent(@RequestParam int limit, @RequestParam long start, @RequestParam long finish) {
        try {
            CTFEventResponseDto response = ctfTimeService.getCTFTimeEvent(limit, start, finish);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getCTFTimeEventById(@PathVariable int id) {
        try {
            CTFEventResponseDto response = ctfTimeService.getCTFTimeEventById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }

    @GetMapping("/events/upcoming")
    public ResponseEntity<?> getCTFTimeEventUpcoming() {
        return null;
    }


    @GetMapping("/teams")
    public ResponseEntity<?> getCTFTimeTeamList() {
        try {
            CTFTeamListResponseDto response = ctfTimeService.getCTFTimeTeamList();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<?> getCTFTimeTeamById(@PathVariable int id) {
        try {
            CTFTeamDetailResponseDto response = ctfTimeService.getCTFTimeTeamDetail(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }



    @GetMapping("/results")
    public ResponseEntity<?> getCTFTimeResult() {
        try {
            CTFEventResultResponseDto response = ctfTimeService.getCTFTimeEventResult();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }

    @GetMapping("/results/{year}")
    public ResponseEntity<?> getCTFTimeResultByYear(@PathVariable int year) {
        try {
            CTFEventResultResponseDto response = ctfTimeService.getCTFTimeEventResultByYear(year);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }



    @GetMapping("/votes/{year}")
    public ResponseEntity<?> getCTFTimeVote(@PathVariable int year) {
        return null;
    }
}