package CUHA.homepage.controller;

import CUHA.homepage.security.dto.CTFTeamDetailResponseDto;
import CUHA.homepage.security.dto.CTFTeamResponseDto;
import CUHA.homepage.service.CTFTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

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
            CTFTeamDetailResponseDto response = ctfTimeService.getCTFTimeTopByCountry(country);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body("값을 불러오지 못했습니다.");
        }
    }



    @GetMapping("/events")
    public ResponseEntity<?> getCTFTimeEvent(@RequestParam int limit, @RequestParam Timestamp start, @RequestParam Timestamp finish) {
        return null;
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getCTFTimeEventById(@PathVariable int id) {
        return null;
    }



    @GetMapping("/teams")
    public ResponseEntity<?> getCTFTimeTeam() {
        return null;
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<?> getCTFTimeTeamById(@PathVariable int id) {
        return null;
    }



    @GetMapping("/results")
    public ResponseEntity<?> getCTFTimeResult() {
        return null;
    }

    @GetMapping("/results/{year}")
    public ResponseEntity<?> getCTFTimeResultByYear(@PathVariable int year) {
        return null;
    }



    @GetMapping("/votes/{year}")
    public ResponseEntity<?> getCTFTimeVote(@PathVariable int year) {
        return null;
    }
}