package CUHA.homepage.controller;

import CUHA.homepage.security.dto.examDTO.*;
import CUHA.homepage.service.ExamService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/api/examcategory")
    public ResponseEntity<Page<ExamFindResponse>> getExamCategory(@RequestParam int page, @RequestParam int size, @RequestParam String category) {
        return ResponseEntity.ok().body(examService.getExamsByCategory(category,page,size));
    }
    @PutMapping("/api/exam")
    public ResponseEntity<ExamMessageResponse> updateExam(@RequestBody ExamUpdateRequest examUpdateRequeest, HttpServletRequest request) {
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
