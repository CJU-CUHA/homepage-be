package CUHA.homepage.service;

import CUHA.homepage.security.dto.boardDTO.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface BoardService {
    BoardmessageResponse addBoard(HttpServletRequest request, BoardRequest board);
    BoardResponse getBoard(Long id);
    BoardmessageResponse deleteBoard(Long id, HttpServletRequest request);
    BoardmessageResponse updateBoard(BoardFindRequest boardFindRequest, HttpServletRequest request);
    List<BoardResponse> getBoards();
}
