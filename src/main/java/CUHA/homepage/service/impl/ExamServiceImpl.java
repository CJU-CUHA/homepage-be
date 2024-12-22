package CUHA.homepage.service.impl;

import CUHA.homepage.model.Board;
import CUHA.homepage.model.Exam;
import CUHA.homepage.model.SolvedExam;
import CUHA.homepage.model.User;
import CUHA.homepage.repository.ExamRepository;
import CUHA.homepage.repository.SolvedExamRepository;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.boardDTO.BoardmessageResponse;
import CUHA.homepage.security.dto.examDTO.*;
import CUHA.homepage.service.ExamService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final SolvedExamRepository solvedExamRepository;

    @Override
    public ExamMessageResponse addExam(ExamRequest examRequest,HttpServletRequest request) {

        Exam exam = Exam.builder()
                .title(examRequest.getTitle())
                .content(examRequest.getContent())
                .score(examRequest.getScore())
                .answer(examRequest.getAnswer())
                .author(userRepository.findByUsername(request.getSession().getAttribute("user").toString()).get())
                .category(examRequest.getCategory())
                .created_at(LocalDateTime.now())
                .build();
        examRepository.save(exam);
        return ExamMessageResponse.builder().message("문제가 등록되었습니다.").build();
    }

    @Override
    public List<ExamFindResponse> getExams() {

        return examRepository.findAll().stream().map(x->ExamFindResponse.builder()
                .id(x.getId())
                .title(x.getTitle())
                .author(x.getAuthor().getUsername())
                .content(x.getContent())
                .score(x.getScore()).build()).toList();
    }

    @Override
    public ExamFindResponse getExam(Long examId) {
        Optional<Exam> exam= examRepository.findById(examId);

        if(!exam.isPresent()){
            throw new NotFoundException("해당 문제가 존재하지 않습니다.");
        }
        Exam getExam=exam.get();
        return ExamFindResponse.builder()
                .id(getExam.getId())
                .title(getExam.getTitle())
                .content(getExam.getContent())
                .author(getExam.getAuthor().getUsername())
                .score(getExam.getScore())
                .category(getExam.getCategory())
                .build();
    }

    @Override
    public ExamMessageResponse checkAnswer(ExamAnswerRequest examAnswerRequest, HttpServletRequest request) {
        Optional<Exam> findexam =examRepository.findById(examAnswerRequest.getId());
        Optional<User> findUserOp=userRepository.findByUsername(request.getSession().getAttribute("user").toString());
        if(!findexam.isPresent()){
            throw new NotFoundException("해당 문제가 존재하지 않습니다.");
        }
        if(!findUserOp.isPresent()){
            throw new NotFoundException("로그인 후 다시 이용해주세요.");
        }
        User findUser=findUserOp.get();
        Optional<SolvedExam> solvedExam=solvedExamRepository.findByUsername(findUser);
        if(solvedExam.isPresent()){
            if(solvedExam.get().isSolved()){
                return ExamMessageResponse.builder().message("이미 푼 문제입니다.").build();
            }
        }
        Exam exam=findexam.get();
        if(exam.getAnswer().equals(examAnswerRequest.getAnswer())){
            findUser.setScore(findUser.getScore()+exam.getScore());
            userRepository.save(findUser);
            solvedExamRepository.save(SolvedExam.builder().username(findUser).exam(findexam.get()).solved(true).build());
            return ExamMessageResponse.builder().message("정답입니다.").build();
        }
        else{
            return ExamMessageResponse.builder().message("오답입니다.").build();
        }
    }

    @Override
    public ExamMessageResponse updateExam(ExamUpdateRequeest examUpdateRequeest, HttpServletRequest request) {
        Optional<Exam> findExam=examRepository.findById(examUpdateRequeest.getId());
        if(!findExam.isPresent()) {
            return ExamMessageResponse.builder().message("존재하지 않는 게시물입니다.").build();
        }
        Exam updateExam=findExam.get();
        if(!request.getSession().getAttribute("user").equals(updateExam.getAuthor().getUsername())) {
            return ExamMessageResponse.builder().message("본인의 게시물만 수정이 가능합니다.").build();
        }
        updateExam.setTitle(examUpdateRequeest.getTitle());
        updateExam.setContent(examUpdateRequeest.getContent());
        updateExam.setScore(examUpdateRequeest.getScore());
        updateExam.setAnswer(examUpdateRequeest.getAnswer());
        updateExam.setCategory(examUpdateRequeest.getCategory());
        examRepository.save(updateExam);
        return ExamMessageResponse.builder().message("수정되었습니다.").build();
    }

    @Override
    public ExamMessageResponse deleteExam(Long examId,HttpServletRequest request) {
        Optional<Exam> findexam=examRepository.findById(examId);
        if(!findexam.isPresent()) {
            return ExamMessageResponse.builder().message("존재하지 않는 게시물입니다.").build();
        }
        Exam deleteExam=findexam.get();
        if(!request.getSession().getAttribute("user").equals(deleteExam.getAuthor().getUsername())) {
            return ExamMessageResponse.builder().message("본인의 게시물만 삭제가 가능합니다.").build();
        }
        examRepository.deleteById(deleteExam.getId());
        return ExamMessageResponse.builder().message("삭제되었습니다.").build();
    }
}
