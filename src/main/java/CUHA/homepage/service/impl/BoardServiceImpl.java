package CUHA.homepage.service.impl;

import CUHA.homepage.model.Board;
import CUHA.homepage.model.BoardFile;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.repository.BoardFileRepository;
import CUHA.homepage.repository.ExamFileRepository;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.boardDTO.*;
import CUHA.homepage.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardFileRepository boardFileRepository;
    private final ExamFileRepository examFileRepository;

    @Override
    public BoardmessageResponse addBoard(HttpServletRequest request, BoardRequest board) {
        Board saveboard = Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .author(userRepository.findByUsername(request.getSession().getAttribute("user").toString()).get())
                .created_at(LocalDateTime.now())
                .build();
        boardRepository.save(saveboard);
        return BoardmessageResponse.builder()
                .message("작성이 완료 되었습니다.").build();
    }

    @Override
    public BoardResponse getBoard(Long id) {
        Board findBoard=boardRepository.findById(id).get();
        return BoardResponse.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .author(findBoard.getAuthor().getUsername())
                .build();
    }

    @Override
    public BoardmessageResponse deleteBoard(Long id, HttpServletRequest request) {
        Optional<Board> board=boardRepository.findById(id);
        if(!board.isPresent()) {
            return BoardmessageResponse.builder().message("존재하지 않는 게시물입니다.").build();
        }
        Board deleteBoard=board.get();
        if(!request.getSession().getAttribute("user").equals(deleteBoard.getAuthor().getUsername())) {
            return BoardmessageResponse.builder().message("본인의 게시물만 삭제가 가능합니다.").build();
        }

        List<BoardFile> fileId= boardFileRepository.findAllByBoard_Id(board.get().getId()).get();
        if(fileId.isEmpty()) {
            return BoardmessageResponse.builder().message("삭제되었습니다.").build();
        }

        for (BoardFile file : fileId) {
            boardFileRepository.deleteByBoard_Id(file.getBoard().getId());
            examFileRepository.deleteByExam_Id(file.getExam().getId());
        }
        boardRepository.deleteById(deleteBoard.getId());
        return BoardmessageResponse.builder().message("삭제되었습니다.").build();
    }

    @Override
    public BoardmessageResponse updateBoard(BoardFindRequest boardFindRequest, HttpServletRequest request) {
        Optional<Board> board=boardRepository.findById(boardFindRequest.getId());
        if(!board.isPresent()) {
            return BoardmessageResponse.builder().message("존재하지 않는 게시물입니다.").build();
        }
        Board updateBoard=board.get();
        if(!request.getSession().getAttribute("user").equals(updateBoard.getAuthor().getUsername())) {
            return BoardmessageResponse.builder().message("본인의 게시물만 수정이 가능합니다.").build();
        }
        updateBoard.setTitle(boardFindRequest.getTitle());
        updateBoard.setContent(boardFindRequest.getContent());
        boardRepository.save(updateBoard);
        return BoardmessageResponse.builder().message("수정되었습니다.").build();
    }

    @Override
    public List<BoardResponse> getBoards() {
        return boardRepository.findAll().stream().map(x-> BoardResponse.builder()
                .id(x.getId())
                .author(x.getAuthor().getUsername())
                .title(x.getTitle())
                .content(x.getContent())
                .build()).toList();
    }
}
