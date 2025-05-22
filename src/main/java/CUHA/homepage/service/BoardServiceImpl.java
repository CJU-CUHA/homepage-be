package CUHA.homepage.service;

import CUHA.homepage.domain.Board;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;
import CUHA.homepage.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board saveBoard = Board.builder()
                .title(boardRequestDto.getTitle())
                .author(boardRequestDto.getAuthor())
                .content(boardRequestDto.getContent())
                .like(0L)
                .dislike(0L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        boardRepository.save(saveBoard);

        return BoardResponseDto.builder()
                .id(saveBoard.getId())
                .title(saveBoard.getTitle())
                .author(saveBoard.getAuthor())
                .content(saveBoard.getContent())
                .build();
    }

    @Override
    public BoardResponseDto getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new BoardNotFoundException(id);
        }

        Board findBoard = board.get();

        return BoardResponseDto.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .author(findBoard.getAuthor())
                .build();
    }

    @Override
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isEmpty()) {
            throw new BoardNotFoundException(id);
        }

        Board updateBoard = board.get();
        // 게시물 소유 확인

        updateBoard.setTitle(boardRequestDto.getTitle());
        updateBoard.setContent(boardRequestDto.getContent());
        boardRepository.save(updateBoard);
        return BoardResponseDto.builder()
                .id(updateBoard.getId())
                .title(updateBoard.getTitle())
                .content(updateBoard.getContent())
                .author(updateBoard.getAuthor())
                .build();

    }

    @Override
    public void deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isEmpty()) {
            throw new BoardNotFoundException(id);

        }

        Board deleteBoard = board.get();
        // 게시물 소유 확인

        // Board File 삭제 로직

        boardRepository.deleteById(id);
        return;
    }

    @Override
    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardResponseDtoList.add(getBoard(board.getId()));
        }
        return boardResponseDtoList;
    }
}
