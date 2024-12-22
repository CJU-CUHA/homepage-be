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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping("/api/exam")
    public ExamMessageResponse postExam(@RequestBody ExamRequest examRequest, HttpServletRequest request) {
        return examService.addExam(examRequest,request);
    }

    @GetMapping("/api/exam")
    public ExamFindResponse getExam(@RequestParam Long id) {
        return examService.getExam(id);
    }

    @GetMapping("/api/exams")
    public List<ExamFindResponse> getExam() {
        return examService.getExams();
    }

    @PutMapping("/api/exam")
    public ExamMessageResponse updateExam(@RequestBody ExamUpdateRequeest examUpdateRequeest, HttpServletRequest request) {
        return examService.updateExam(examUpdateRequeest,request);
    }

    @DeleteMapping("/api/exam")
    public ExamMessageResponse deleteExam(@RequestParam Long id, HttpServletRequest request) {
        return examService.deleteExam(id,request);
    }

    @PostMapping("/api/checkAnswer")
    public ExamMessageResponse checkAnswer(@RequestBody ExamAnswerRequest answer, HttpServletRequest request) {
        return examService.checkAnswer(answer,request);
    }

}
