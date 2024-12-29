package CUHA.homepage.controller;

import CUHA.homepage.security.dto.boardDTO.BoardFindRequest;
import CUHA.homepage.security.dto.boardDTO.BoardRequest;
import CUHA.homepage.security.dto.boardDTO.BoardResponse;
import CUHA.homepage.security.dto.boardDTO.BoardmessageResponse;
import CUHA.homepage.security.dto.examDTO.*;
import CUHA.homepage.service.BoardService;
import CUHA.homepage.service.ExamService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping("/api/exam")
    public ResponseEntity<ExamMessageResponse> postExam(@RequestBody ExamRequest examRequest, HttpServletRequest request) {
        return ResponseEntity.ok().body(examService.addExam(examRequest,request));
    }

    @GetMapping("/api/exam")
    public ResponseEntity<ExamFindResponse> getExam(@RequestParam Long id) {
        return ResponseEntity.ok().body(examService.getExam(id));
    }

    @GetMapping("/api/exams")
    public ResponseEntity<List<ExamFindResponse>> getExam() {
        return ResponseEntity.ok().body(examService.getExams());
    }

    @PutMapping("/api/exam")
    public ResponseEntity<ExamMessageResponse> updateExam(@RequestBody ExamUpdateRequeest examUpdateRequeest, HttpServletRequest request) {
        return ResponseEntity.ok().body(examService.updateExam(examUpdateRequeest,request));
    }

    @DeleteMapping("/api/exam")
    public ResponseEntity<ExamMessageResponse> deleteExam(@RequestParam Long id, HttpServletRequest request) {
        return ResponseEntity.ok().body(examService.deleteExam(id,request));
    }

    @PostMapping("/api/checkAnswer")
    public ResponseEntity<ExamMessageResponse> checkAnswer(@RequestBody ExamAnswerRequest answer, HttpServletRequest request) {
        return ResponseEntity.ok().body(examService.checkAnswer(answer,request));
    }

}
