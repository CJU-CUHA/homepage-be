package CUHA.homepage.board.service;

import CUHA.homepage.board.Board;
import CUHA.homepage.board.repository.BoardRepository;
import CUHA.homepage.board.security.dto.BoardFindRequestDto;
import CUHA.homepage.board.security.dto.BoardMessageResponseDto;
import CUHA.homepage.board.security.dto.BoardRequestDto;
import CUHA.homepage.board.security.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardMessageResponseDto createBoard(BoardRequestDto boardRequestDto) {
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

        return BoardMessageResponseDto.builder()
                .message("작성이 완료 되었습니다.")
                .build();
    }

    @Override
    public BoardResponseDto readBoard(Long id) {

        Board findBoard = boardRepository.findById(id).get();

        return BoardResponseDto.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .author(findBoard.getAuthor())
                .build();
    }

    @Override
    public BoardMessageResponseDto updateBoard(BoardFindRequestDto boardFindRequestDto) {
        Optional<Board> board = boardRepository.findById(boardFindRequestDto.getId());
        if(!board.isPresent()) {
            return BoardMessageResponseDto.builder()
                    .message("존재하지 않는 게시물입니다.")
                    .build();
        }

        Board updateBoard = board.get();
        // 게시물 소유 확인

        updateBoard.setTitle(boardFindRequestDto.getTitle());
        updateBoard.setContent(boardFindRequestDto.getContent());
        boardRepository.save(updateBoard);
        return BoardMessageResponseDto.builder()
                .message("수정 되었습니다.")
                .build();

    }

    @Override
    public BoardMessageResponseDto deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(!board.isPresent()) {
            return BoardMessageResponseDto.builder()
                    .message("존재하지 않는 게시물입니다.")
                    .build();
        }

        Board deleteBoard = board.get();
        // 게시물 소유 확인

        // Board File 삭제 로직

        boardRepository.deleteById(id);
        return BoardMessageResponseDto.builder()
                .message("삭제 되었습니다.")
                .build();
    }

    @Override
    public List<BoardResponseDto> getBoards() {
        return null;
    }
}
