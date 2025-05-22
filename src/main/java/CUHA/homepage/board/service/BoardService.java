package CUHA.homepage.board.service;

import CUHA.homepage.board.security.dto.BoardFindRequestDto;
import CUHA.homepage.board.security.dto.BoardMessageResponseDto;
import CUHA.homepage.board.security.dto.BoardRequestDto;
import CUHA.homepage.board.security.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    BoardMessageResponseDto createBoard(BoardRequestDto boardRequestDto);
    BoardResponseDto readBoard(Long id);
    BoardMessageResponseDto updateBoard(BoardFindRequestDto boardFindRequestDto);
    BoardMessageResponseDto deleteBoard(Long id);
    List<BoardResponseDto> getBoards();
}
