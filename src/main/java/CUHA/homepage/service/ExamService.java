package CUHA.homepage.service;

import CUHA.homepage.security.dto.examDTO.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ExamService {
    ExamMessageResponse addExam(ExamRequest examRequest,HttpServletRequest request);
    List<ExamFindResponse> getExams();
    ExamFindResponse getExam(Long examId);
    ExamMessageResponse checkAnswer(ExamAnswerRequest examAnswerRequest, HttpServletRequest request);
    ExamMessageResponse updateExam(ExamUpdateRequeest examUpdateRequeest, HttpServletRequest request);
    ExamMessageResponse deleteExam(Long examId,HttpServletRequest request);
}
