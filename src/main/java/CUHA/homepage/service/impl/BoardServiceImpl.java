package CUHA.homepage.service.impl;

import CUHA.homepage.domain.Board;
import CUHA.homepage.domain.User;
import CUHA.homepage.exception.TokenNotFoundException;
import CUHA.homepage.exception.UserNotFoundException;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;
import CUHA.homepage.exception.BoardNotFoundException;
import CUHA.homepage.security.jwt.JWTUtil;
import CUHA.homepage.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;


    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {

        Board saveBoard = Board.builder()
                .title(boardRequestDto.getTitle())
                .author(userRepository.findById(boardRequestDto.getAuthor()).get())
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
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, String token) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isEmpty()) {
            throw new BoardNotFoundException(id);
        }
        Board updateBoard = board.get();

        if(token == null) {
            throw new TokenNotFoundException();
        }

        String username = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        User user = userOptional.get();

        if(!updateBoard.getAuthor().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

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
    public void deleteBoard(Long id, String token) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isEmpty()) {
            throw new BoardNotFoundException(id);
        }

        Board deleteBoard = board.get();

        if(token == null) {
            throw new TokenNotFoundException();
        }

        String username = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        User user = userOptional.get();

        if(!deleteBoard.getAuthor().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        // Board File 삭제 로직

        boardRepository.deleteById(id);
        return;
    }

    @Override
    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();

        return boardList.stream().map(BoardResponseDto::from).toList();
    }

    @Override
    public Page<BoardResponseDto> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findAll(pageable);

        return boards.map(BoardResponseDto::from);
    }

    @Override
    public Page<BoardResponseDto> getBoardsByAuthor(Long author, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findByAuthor(author, pageable);

        return boards.map(BoardResponseDto::from);
    }

    @Override
    public Page<BoardResponseDto> getBoardsByTitle(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findByTitleContaining(keyword, pageable);

        return boards.map(BoardResponseDto::from);
    }

    @Override
    public Page<BoardResponseDto> getBoardsByContent(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findByContentContaining(keyword, pageable);

        return boards.map(BoardResponseDto::from);
    }
}
