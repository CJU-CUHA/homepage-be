package CUHA.homepage.service;

import CUHA.homepage.model.News;
import CUHA.homepage.security.dto.boardDTO.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    BoardmessageResponse addBoard(HttpServletRequest request, BoardRequest board);
    BoardResponse getBoard(Long id);
    BoardmessageResponse deleteBoard(Long id, HttpServletRequest request);
    BoardmessageResponse updateBoard(BoardFindRequest boardFindRequest, HttpServletRequest request);
    List<BoardResponse> getBoards();

    Page<BoardResponse> getBoardsPage(int page,int size);
    Page<BoardResponse> getBoardsPageByAuthor(int page,int size,String author);


}
