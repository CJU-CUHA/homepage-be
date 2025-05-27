package CUHA.homepage.service;

import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardRequestDto boardRequestDto);
    BoardResponseDto getBoard(Long id);
    BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, String token);
    void deleteBoard(Long id, String token);
    List<BoardResponseDto> getBoards();
}
